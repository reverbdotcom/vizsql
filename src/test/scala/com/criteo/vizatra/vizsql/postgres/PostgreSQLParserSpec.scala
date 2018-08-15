package com.criteo.vizatra.vizsql

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, EitherValues, PropSpec}

class PostgreSQLParserSpec extends PropSpec with Matchers with EitherValues with TableDrivenPropertyChecks {

  val validPostgreSQLSelectStatements = TableDrivenPropertyChecks.Table(
    ("SQL", "Expected AST"),
    ("""select CAST(12 as timestamptz)""", SimpleSelect(
      projections = List(
        ExpressionProjection(
          CastExpression(
            from = LiteralExpression(IntegerLiteral(12)),
            to = TimestampTzTypeLiteral
          )
        )
      )
    )),
    ("""select CAST(12 as timestamp without time zone)""", SimpleSelect(
      projections = List(
        ExpressionProjection(
          CastExpression(
            from = LiteralExpression(IntegerLiteral(12)),
            to = TimestampWithoutTimeZoneTypeLiteral
          )
        )
      )
    )),
    ("""select 15::timestamptz""", SimpleSelect(
      projections = List(
        ExpressionProjection(
          CastExpression(
            from = LiteralExpression(IntegerLiteral(15)),
            to = TimestampTzTypeLiteral
          )
        )
      )
    ))
  )

  // --

  property("parse to correct types") {
    forAll(validPostgreSQLSelectStatements) {
      case (sql, expectedAst) =>
        (new PostgreSQLParser).parseStatement(sql)
          .fold(e => sys.error(s"\n\n${e.toString(sql)}\n"), identity) should be (expectedAst)
    }
  }
}
