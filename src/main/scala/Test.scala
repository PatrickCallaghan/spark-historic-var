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

object HistoricVar extends UsersSparkJob {

  override def runJob(sc: SparkContext, config: Config) = {
	  val rdd = sc.cassandraTable("datastax_creditcard_demo", "latest_transactions").cache

	  println("Latest Transactions : " + rdd.collect.size);
	  
	  rdd.collect.size;
  }
}

