import scala.io.Source
import scalax.collection.mutable.Graph
import scalax.collection.GraphPredef._, scalax.collection.GraphEdge._
import scala.collection.mutable.ListBuffer


object CalcPoints {
  val inviters = new ListBuffer[String]
  val allPeople = new ListBuffer[String]
  val g = Graph((-1)~>(-1), -1)

	def main(args: Array[String]): Unit = {
		
    
		for(line <- Source.fromFile(args(0)).getLines()) {
        registerEdge(line)			
  		}

    g -= (-1)
    getPoints()
    for (newEdge <- io.Source.stdin.getLines) {
      if (newEdge.isEmpty)
        return

      registerEdge(newEdge)
      getPoints()
    }
	}

  def registerEdge(newEdge: String): Unit = {
    val nodes = newEdge.split(" ")

    if (! (inviters contains nodes(0)) ) 
      inviters += nodes(0)
    /*Check if person invited is already in the list. If so,
    the inviter won't get the points */
    if (! (allPeople contains nodes(1))) {
      implicit val factory = scalax.collection.GraphEdge.DiEdge
      g.addEdge(nodes(0).toInt,nodes(1).toInt)
    }
    if (! (allPeople contains nodes(0)) )
      allPeople += nodes(0)
    else if (! (allPeople contains nodes(1)) )
      allPeople += nodes(1)
  }

  def getPoints(): Unit = {
    println("Calculing the points for the people so far listed.")
    for (person <- allPeople) {
      val points = calcNodeValue(person, 0)
      println("Person " + person + " get " + points + " point(s)")
    }
  }

  def calcNodeValue(person: String, weight: Int): Double = {
    var sum = 0.0
    val node = g get person.toInt
    val childs = node.diSuccessors
    
    for (child <- childs) {
      if (inviters contains child.toString) {
        sum += (scala.math.pow( (0.5), weight) + calcNodeValue(child.toString, weight + 1))
      }
    }
    return sum
  }
}