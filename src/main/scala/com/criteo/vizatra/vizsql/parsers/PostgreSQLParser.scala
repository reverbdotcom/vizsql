package com.criteo.vizatra.vizsql

import scala.util.parsing.combinator._
import scala.util.parsing.combinator.lexical._
import scala.util.parsing.combinator.syntactical._
import scala.util.parsing.input.CharArrayReader.EofCh

object PostgreSQLParser {
  import SQL99Parser._

  val postgresTypeMap: Map[String, TypeLiteral] = typeMap ++ Seq(
    "timestamptz" -> TimestampTzTypeLiteral,
    "timestamp without time zone" -> TimestampWithoutTimeZoneTypeLiteral
  )
}

class PostgreSQLParser extends SQL99Parser {
  override val typeMap = PostgreSQLParser.postgresTypeMap

  val typeParser: PartialFunction[List[Elem], TypeLiteral] = { 
    case ts: List[Elem] if typeMap.contains(ts.map(_.chars).mkString(" ").toLowerCase) => typeMap(ts.map(_.chars).mkString(" ").toLowerCase)
  }

  lazy val word = elem("ident", _.chars.forall(Character.isLetterOrDigit(_)))

  override lazy val typeLiteral: Parser[TypeLiteral] =
    rep1(word) ^? typeParser

  override lazy val cast =
    ("cast" ~ "(") ~> expr ~ ("as" ~> typeLiteral <~ ")") ^^ { case e ~ t => CastExpression(e, t) } |
    expr ~ ("::" ~> typeLiteral) ^^ { case e ~ t => CastExpression(e, t) } 

  override lazy val simpleExpr = (_: Parser[Expression]) =>
    (cast
    | literal                ^^ LiteralExpression
    | function
    | countStar
    | caseWhen
    | column                 ^^ ColumnExpression
    | "(" ~> select <~ ")"   ^^ SubSelectExpression
    | "(" ~> expr <~ ")"     ^^ ParenthesedExpression
    | expressionPlaceholder
    )
}
