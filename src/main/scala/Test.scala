import com.datastax.spark.connector._
import com.datastax.spark.connector.cql.CassandraConnector

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD

import com.typesafe.config.Config

trait UsersSparkJob extends spark.jobserver.SparkJob with spark.jobserver.NamedRddSupport  {
  
  // Validation is not really needed in this example
  def validate(sc: SparkContext, config: Config): spark.jobserver.SparkJobValidation = spark.jobserver.SparkJobValid
}

case class UserInteraction (user_id: String, app: String, time: java.util.Date, action: String)

object HistoricVar extends UsersSparkJob {

  override def runJob(sc: SparkContext, config: Config) = {
	  val interactionsRdd = sc.cassandraTable[UserInteraction]("datastax_user_interactions_demo", "user_interactions").cache
	  this.namedRdds.update("dictionary", interactionsRdd)
	  
	  println("User Interactions : " + interactionsRdd.collect.size);
	  
	  interactionsRdd.collect.size;
  }
}

