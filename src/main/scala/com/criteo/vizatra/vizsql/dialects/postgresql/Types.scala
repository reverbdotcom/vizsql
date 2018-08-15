package com.criteo.vizatra.vizsql

case class TIMESTAMPTZ(nullable: Boolean = false) extends Type {
  def withNullable(nullable: Boolean = nullable) = this.copy(nullable).asInstanceOf[this.type]
  def canBeCastTo(other: Type): Boolean = other match {
    case TIMESTAMP(_) => true
    case _ => false
  }
  def show = "timestamp"
}

case class TIMESTAMPWITHOUTTIMEZONE(nullable: Boolean = false) extends Type {
  def withNullable(nullable: Boolean = nullable) = this.copy(nullable).asInstanceOf[this.type]
  def canBeCastTo(other: Type): Boolean = other match {
    case TIMESTAMP(_) => true
    case _ => false
  }
  def show = "timestamp without time zone"
}
