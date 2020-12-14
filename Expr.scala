package edu.colorado.csci3155.project1

sealed trait Expr
case class Const(f: Double) extends  Expr
case class Plus(e1: Expr, e2: Expr) extends  Expr
case class Minus(e1: Expr, e2: Expr) extends  Expr
case class Mult(e1: Expr, e2: Expr) extends  Expr
case class Div(e1: Expr, e2: Expr) extends  Expr
case class Exp(e:Expr) extends Expr
case class Log(e: Expr) extends Expr
case class Sine(e: Expr) extends Expr
case class Cosine(e:Expr) extends Expr

def compileExpr(e: Expr): List[StackMachineInstruction] = {
  e match{
    case Const(x) => {
    val newList = List(PushI(x))
    newList
  }
    case Plus(e1, e2) => {
    val l1 = compileExpr(e1)
    val l2 = compileExpr(e2)
    val newList = l1 ++ l2 ++ List(AddI)
    newList
  }
    case Minus(e1, e2) => {
    val l1 = compileExpr(e1)
    val l2 = compileExpr(e2)
    val newList = l1 ++ l2 ++ List(SubI)
    newList
  }
    case Mult(e1, e2) => {
    val l1 = compileExpr(e1)
    val l2 = compileExpr(e2)
    val newList = l1 ++ l2 ++ List(MultI)
    newList
  }
    case Div(e1, e2) => {
    val l1 = compileExpr(e1)
    val l2 = compileExpr(e2)
    val newList = l1 ++ l2 ++ List(DivI)
    newList
  }
    case Exp(e) => {
    val l1 = compileExpr(e)
    val newList = l1 ++ List(ExpI)
    newList
  }
    case Log(e) => {
    val l1 = compileExpr(e)
    val newList = l1 ++ List(LogI)
    newList
  }
    case Sine(e) => {
    val l1 = compileExpr(e)
    val newList = l1 ++ List(SinI)
    newList
  }
    case Cosine(e) => {
    val l1 = compileExpr(e)
    val newList = l1 ::: List(CosI)
    newList
  }
  }
}

