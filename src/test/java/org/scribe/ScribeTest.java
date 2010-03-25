/* 
Copyright 2010 Pablo Fernandez

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package org.scribe;

import java.io.*;
import java.util.*;

import org.junit.*;
import org.scribe.http.*;
import org.scribe.http.Request.*;

import static org.junit.Assert.*;

public class ScribeTest {

  Scribe scribe;
  
  @Before
  public void setup() throws Exception{
    Properties props = new Properties();
    FileInputStream fis = new FileInputStream("src/main/resources/org/scribe/providers/linkedin.properties");
    props.load(fis);
    scribe = Scribe.getInstance(props);  
  }
  
  @Test
  public void shouldRetrieveRequestToken(){
    String requestToken = scribe.getRequestToken();
    System.out.println(requestToken);
    assertTrue(requestToken.length() > 0);
  }
  
  @Test @Ignore
  public void shouldRetrieveAccessToken(){
    //MAKE SURE YOU VALIDATE THE TOKEN IN THE PREVIOUS TEST, AND COPY THE VERIFIER
    String accessToken = scribe.getAccessToken("RequestToken", "RequestTokenSecret", "Verifier");
    System.out.println(accessToken);
    assertTrue(accessToken.length() > 0);
  }
  
  @Test @Ignore
  public void shouldAccessProtectedResource(){
    //MAKE SURE YOU GET THE ACCESS TOKEN IN THE PREVIOUS TEST
    Request request = new Request(Verb.GET, "https://api.linkedin.com/v1/people/~/network?count=50");
    scribe.signRequest(request, "AccessToken", "AccessTokenSecret");
    Response response = request.send();
    System.out.println(response.getBody());
    assertTrue(response.getCode() == 200);
  }
}