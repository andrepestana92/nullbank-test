import scala.io.Source
import scalax.collection.mutable.Graph
import scalax.collection.GraphPredef._, scalax.collection.GraphEdge._


object CalcPoints {
	def main(args: Array[String]): Unit = {
		val g = Graph((-1)~(-1), -1)
		for(line <- Source.fromFile(args(0)).getLines()) {
  			val nodes = line.split(" ")
  			val peopleInList = g.nodes mkString " "

  			/*Check if person invited is already in the list. If so,
  			the inviter won't get the points*/
  			if (! (peopleInList contains nodes(1))) {
  				implicit val factory = scalax.collection.GraphEdge.UnDiEdge
  				g.addEdge(nodes(0).toInt,nodes(1).toInt)
  			}
  		}
  		println(g.edges mkString " ")
	}
}