package edu.colorado.csci3155.project1

import scala.compat.Platform.StackOverflowError
import scala.math

sealed trait StackMachineInstruction
case object AddI extends StackMachineInstruction
case object SubI extends StackMachineInstruction
case object MultI extends StackMachineInstruction
case object DivI extends StackMachineInstruction
case object ExpI extends StackMachineInstruction
case object LogI extends StackMachineInstruction
case object SinI extends StackMachineInstruction
case object CosI extends StackMachineInstruction
case class PushI(f: Double) extends StackMachineInstruction
case object PopI extends StackMachineInstruction


object StackMachineEmulator {

    /* Function emulateSingleInstruction
        Given a list of doubles to represent a stack and a single instruction of type StackMachineInstruction
        Return a stack that results when the instruction is executed from the stack.
        Make sure you handle the error cases: eg., stack size must be appropriate for the instruction
        being executed. Division by zero, log of a non negative number
        Throw an exception or assertion violation when error happens.

     */
    def emulateSingleInstruction(stack: List[Double], ins: StackMachineInstruction): List[Double] = {
        ins match{
            case PushI(d) => {
                val temp = d +: stack
                return temp
            }

            case PopI => {
                if(stack.isEmpty){throw new StackOverflowError}
                val newStack = stack.tail
                return newStack
            }

            case AddI => {
                if(stack.isEmpty){throw new StackOverflowError}
                val v1 = stack.head
                val tempStack1 = stack.tail
                if(stack.isEmpty){throw new StackOverflowError}
                val v2 = tempStack1.head
                val tempStack2 = tempStack1.tail
                val sum = v1 + v2
                val newStack = sum +: tempStack2
                newStack
            }

            case SubI => {
                if(stack.isEmpty){throw new StackOverflowError}
                val v1 = stack.head
                val tempStack1 = stack.tail
                if(stack.isEmpty){throw new StackOverflowError}
                val v2 = tempStack1.head
                val tempStack2 = tempStack1.tail
                val sum = v2 - v1
                val newStack = sum +: tempStack2
                newStack
            }

            case MultI => {
                if(stack.isEmpty){throw new StackOverflowError}
                val v1 = stack.head
                val tempStack1 = stack.tail
                if(stack.isEmpty){throw new StackOverflowError}
                val v2 = tempStack1.head
                val tempStack2 = tempStack1.tail
                val sum = v2 * v1
                val newStack = sum +: tempStack2
                newStack
            }

            case DivI => {
                if(stack.isEmpty){throw new StackOverflowError}
                val v1 = stack.head
                if(v1 == 0){throw new StackOverflowError}
                val tempStack1 = stack.tail
                if(stack.isEmpty){throw new StackOverflowError}
                val v2 = tempStack1.head
                val tempStack2 = tempStack1.tail
                val sum = v2 / v1
                val newStack = sum +: tempStack2
                newStack
            }

            case LogI => {
                if(stack.isEmpty){throw new StackOverflowError}
                val v1 = stack.head
                if(v1 < 0){throw new Exception}
                val tempStack1 = stack.tail
                val ans = math.log(v1)
                val newStack = ans +: tempStack1
                newStack
            }

            case ExpI => {
                if(stack.isEmpty){throw new StackOverflowError}
                val v1 = stack.head
                val tempStack1 = stack.tail
                val ans = math.exp(v1)
                val newStack = ans +: tempStack1
                newStack
            }

            case SinI => {
                if(stack.isEmpty){throw new StackOverflowError}
                val v1 = stack.head
                val tempStack1 = stack.tail
                val ans = math.sin(v1)
                val newStack = ans +: tempStack1
                newStack
            }

            case CosI => {
                if(stack.isEmpty){throw new StackOverflowError}
                val v1 = stack.head
                val tempStack1 = stack.tail
                val ans = math.cos(v1)
                val newStack = ans +: tempStack1
                newStack
            }

        }
    }


    /* Function emulateStackMachine
       Execute the list of instructions provided as inputs using the
       emulateSingleInstruction function.
       Use foldLeft over list of instruction rather than a for loop if you can.
       Return value must be a double that is the top of the stack after all instructions
       are executed.
     */
    def emulateStackMachine(instructionList: List[StackMachineInstruction]): Double = {
        val stack:List[Double] = List()
        instructionList.foldLeft(stack){(x: List[Double], v: StackMachineInstruction) =>  emulateSingleInstruction(x, v)}.head
    }

}