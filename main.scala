import scala.io.Source
import scalax.collection.mutable.Graph
import scalax.collection.GraphPredef._, scalax.collection.GraphEdge._


object CalcPoints {
	def main(args: Array[String]): Unit = {
		val g = Graph(0~0, 0)
		for(line <- Source.fromFile(args(0)).getLines()) {
  			val nodes = line.split(" ")
  			implicit val factory = scalax.collection.GraphEdge.UnDiEdge
  			g.addEdge(nodes(0).toInt,nodes(1).toInt)
  		}
	}
}