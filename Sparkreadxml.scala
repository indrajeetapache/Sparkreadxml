
# JSON
spark.read.json("sample/json/")

# CSV
spark.read.csv("sample/csv/", header=True, inferSchema=True)

# XML
spark.read.format("com.databricks.spark.xml") \
    .options(rowTag="book").load("sample/xml/")
    
When the dataset is huge then play around with sampling ratio

# JSON
spark.read.options(samplingRatio=0.1).json("sample/json/")

# XML
spark.read.format("com.databricks.spark.xml") \
    .options(rowTag="book") \
    .options(samplingRatio=0.1) \
    .load("sample/xml/")
    
Hint 2
Use static schema .

from pyspark.sql.types import *

# This is a simplified schema of stackoverflow's posts collection
schema = StructType([
    StructField('Id', IntegerType()),
    StructField('AcceptedAnswerId', IntegerType()),
    StructField('AnswerCount', IntegerType()),
    StructField('ClosedDate', TimestampType()),
    StructField('CommentCount', IntegerType()),
    StructField('CreationDate', TimestampType()),
    StructField('FavoriteCount', IntegerType()),
    StructField('LastActivityDate', TimestampType()),
    StructField('OwnerDisplayName', StringType()),
    StructField('OwnerUserId', IntegerType()),
    StructField('ParentId', IntegerType()),
    StructField('Score', IntegerType()),
    StructField('Title', StringType()),
    StructField('ViewCount', IntegerType())
])

# JSON
df = spark.read.json("sample/json/", schema=schema)

# CSV
df = spark.read.csv("sample/csv/", schema=schema, header=True)

# XML
df = spark.read.format("com.databricks.spark.xml") \
       .options(rowTag="post").load("sample/xml/", schema=schema)
