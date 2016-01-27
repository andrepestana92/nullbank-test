import scala.io.Source
import scalax.collection.mutable.Graph
import scalax.collection.GraphPredef._, scalax.collection.GraphEdge._
import scala.collection.mutable.ListBuffer


object CalcPoints {
  val inviters = new ListBuffer[String]
  val g = Graph((-1)~>(-1), -1)

	def main(args: Array[String]): Unit = {
		
    
		for(line <- Source.fromFile(args(0)).getLines()) {
  			val nodes = line.split(" ")
  			val peopleInList = g.nodes mkString " "

        inviters += nodes(0)
  			/*Check if person invited is already in the list. If so,
  			the inviter won't get the points */
  			if (! (peopleInList contains nodes(1))) {
  				implicit val factory = scalax.collection.GraphEdge.DiEdge
  				g.addEdge(nodes(0).toInt,nodes(1).toInt)
  			}
  		}

      g -= (-1)
      val allPeople = (g.nodes mkString " ").split(" ")

      for (person <- allPeople) {
        val points = calcNodeValue(person, 0)
        println(person + " " + points)
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