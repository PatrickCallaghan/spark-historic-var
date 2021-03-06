import com.datastax.spark.connector._
import com.datastax.spark.connector.cql.CassandraConnector

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD

import com.typesafe.config.Config

trait UsersSparkJob1 extends spark.jobserver.SparkJob with spark.jobserver.NamedRddSupport  {
  
  // Validation is not really needed in this example
  def validate(sc: SparkContext, config: Config): spark.jobserver.SparkJobValidation = spark.jobserver.SparkJobValid
}

object HistoricVar1 extends UsersSparkJob {

  override def runJob(sc: SparkContext, config: Config) = {
    
      println("Getting data from cache");
	  val interactionsRdd = this.namedRdds.get[UserInteraction]("dictionary").get

	  println("User Interactions : " + interactionsRdd.count);
	  
	  interactionsRdd.collect;
  }
}

