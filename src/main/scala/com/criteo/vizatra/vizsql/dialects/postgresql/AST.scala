package com.criteo.vizatra.vizsql

import Show._

case object TimestampTzTypeLiteral extends TypeLiteral {
  val mapType = TIMESTAMPTZ()
  def show = keyword("timestamptz")
}

case object TimestampWithoutTimeZoneTypeLiteral extends TypeLiteral {
  val mapType = TIMESTAMPWITHOUTTIMEZONE()
  def show = keyword("timestamp without time zone")
}

