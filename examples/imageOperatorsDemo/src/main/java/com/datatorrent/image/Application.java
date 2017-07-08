/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.datatorrent.image;

import com.datatorrent.api.DAG;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import org.apache.hadoop.conf.Configuration;

@ApplicationAnnotation(name = "ImageConversionCompressionResize")
/**
 * Reads images from a directory- converts,compresses and resize the images and writes to output directory.
 * Set values in properties.xml
 */
public class Application implements StreamingApplication
{

  public void populateDAG(DAG dag, Configuration configuration)
  {
    /**
     * Add dag operators.
     */
    ImageReader imageReader = dag.addOperator("ImageReader",ImageReader.class);
    ImageFormatConversionOperator imageFormatConversionOperator = dag.addOperator("ConvertToJpeg",ImageFormatConversionOperator.class);
    ImageCompressionOperator imageCompressionOperator = dag.addOperator("CompressImage",ImageCompressionOperator.class);
    ImageResizeOperator imageResizeOperator = dag.addOperator("ResizeImage",ImageResizeOperator.class);
    BytesFileWriter bytesFileWriter = dag.addOperator("ImageWriter",BytesFileWriter.class);
    /**
     * Add dag streams.
     */
    dag.addStream("Read to convert",imageReader.output,imageFormatConversionOperator.input);
    dag.addStream("Convert to compress",imageFormatConversionOperator.output,imageCompressionOperator.input);
    dag.addStream("Compress to resize",imageCompressionOperator.output,imageResizeOperator.input);
    dag.addStream("Resize to Write",imageResizeOperator.output,bytesFileWriter.input);
    
  }
}
