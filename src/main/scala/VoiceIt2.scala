package voiceit2

import scalaj.http._
import java.io.{File, FileInputStream, IOException}
import java.nio.file.{Paths, Files}

class VoiceIt2(val key : String, val token : String) {
  val apikey = key
  val apitoken = token
  val baseUrl : String = "https://api.voiceit.io"
  val version : String = "2.2.0"
  var notificationUrl : String = ""
  val header = Seq("platformId" -> "43", "platformVersion" -> version)
  val connTimeoutMs = 60000
  val readTimeoutMs = 60000

  def pathToByteArray(path : String) : Array[Byte] = {
    val is = new FileInputStream(path)
    val cnt = is.available
    val bytes = Array.ofDim[Byte](cnt)
    is.read(bytes)
    is.close()
      return bytes
  }

  def getVersion() : String = {
    return version
  }

  def addNotificationUrl(url : String) : Unit = {
    notificationUrl = url
  }

  def removeNotificationUrl() : Unit = {
    notificationUrl = ""
  }

  def getAllUsers() : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/users").headers(header).auth(apikey, apitoken).asString.body
    } else {
      return Http(baseUrl + "/users").param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).asString.body
    }
  }

  def createUser() : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/users").headers(header).auth(apikey, apitoken).postMulti().asString.body
    } else {
      return Http(baseUrl + "/users").param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).postMulti().asString.body
    }
  }

  def checkUserExists(userId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/users/" + userId).headers(header).auth(apikey, apitoken).asString.body
    } else {
      return Http(baseUrl + "/users/" + userId).param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).asString.body
    }
  }

  def deleteUser(userId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/users/" + userId).headers(header).auth(apikey, apitoken).method("DELETE").asString.body
    } else {
      return Http(baseUrl + "/users/" + userId).param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).method("DELETE").asString.body
    }
  }

  def getGroupsForUser(userId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/users/" + userId + "/groups").headers(header).auth(apikey, apitoken).asString.body
    } else {
      return Http(baseUrl + "/users/" + userId + "/groups").param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).asString.body
    }
  }


  def getAllGroups() : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/groups").headers(header).auth(apikey, apitoken).asString.body
    } else {
      return Http(baseUrl + "/groups").param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).asString.body
    }
  }

  def getGroup(groupId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/groups/" + groupId).headers(header).auth(apikey, apitoken).asString.body
    } else {
      return Http(baseUrl + "/groups/" + groupId).param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).asString.body
    }
  }

  def checkGroupExists(groupId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/groups/" + groupId + "/exists").headers(header).auth(apikey, apitoken).asString.body
    } else {
      return Http(baseUrl + "/groups/" + groupId + "/exists").param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).asString.body
    }
  }

  def createGroup(description : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/groups").headers(header).auth(apikey, apitoken).postMulti(MultiPart("description", "description", "text/plain", description)).asString.body
    } else {
      return Http(baseUrl + "/groups").param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).postMulti(MultiPart("description", "description", "text/plain", description)).asString.body
    }
  }

  def addUserToGroup(groupId : String, userId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/groups/addUser").headers(header).auth(apikey, apitoken).postForm(Seq("groupId" -> groupId, "userId" -> userId)).method("PUT").asString.body
    } else {
      return Http(baseUrl + "/groups/addUser").param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).postForm(Seq("groupId" -> groupId, "userId" -> userId)).method("PUT").asString.body
    }
  }

  def removeUserFromGroup(groupId : String, userId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/groups/removeUser").headers(header).auth(apikey, apitoken).postForm(Seq("groupId" -> groupId, "userId" -> userId)).method("PUT").asString.body
    } else {
      return Http(baseUrl + "/groups/removeUser").param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).postForm(Seq("groupId" -> groupId, "userId" -> userId)).method("PUT").asString.body
    }
  }

  def deleteGroup(groupId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/groups/" + groupId).headers(header).auth(apikey, apitoken).method("DELETE").asString.body
    } else {
      return Http(baseUrl + "/groups/" + groupId).param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).method("DELETE").asString.body
    }
  }

  def getAllVoiceEnrollments(userId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/voice/" + userId).headers(header).auth(apikey, apitoken).asString.body
    } else {
      return Http(baseUrl + "/enrollments/voice/" + userId).param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).asString.body
    }
  }

  def getAllVideoEnrollments(userId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/video/" + userId).headers(header).auth(apikey, apitoken).asString.body
    } else {
      return Http(baseUrl + "/enrollments/video/" + userId).param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).asString.body
    }
  }

  def getAllFaceEnrollments(userId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/face/" + userId).headers(header).auth(apikey, apitoken).asString.body
    } else {
      return Http(baseUrl + "/enrollments/face/" + userId).param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).asString.body
    }
  }

  def createVoiceEnrollment(userId : String, lang : String, phrase : String, filePath : String) : String = {
    if (!Files.exists(Paths.get(filePath))) {
      throw new IOException("File " + filePath + " not found") 
    }
    return createVoiceEnrollment(userId, lang, phrase, pathToByteArray(filePath))
  }

  def createVoiceEnrollment(userId : String, lang : String, phrase : String, file : Array[Byte]) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/voice")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "phrase" -> phrase))
        .postMulti(MultiPart("recording", "recording", "audio/wav", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/enrollments/voice")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "phrase" -> phrase))
        .postMulti(MultiPart("recording", "recording", "audio/wav", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def createVoiceEnrollmentByUrl(userId : String, lang : String, phrase : String, url : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/voice/byUrl")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "fileUrl" -> url, "phrase" -> phrase))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/enrollments/voice/byUrl")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "fileUrl" -> url, "phrase" -> phrase))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def createFaceEnrollment(userId : String, filePath : String) : String = {
    if (!Files.exists(Paths.get(filePath))) {
      throw new IOException("File " + filePath + " not found") 
    }
    return createFaceEnrollment(userId, pathToByteArray(filePath))
  }

  def createFaceEnrollment(userId : String, file : Array[Byte]) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/face")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId))
        .postMulti(MultiPart("video", "video", "video/mp4", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/enrollments/face")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId))
        .postMulti(MultiPart("video", "video", "video/mp4", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def createFaceEnrollmentByUrl(userId : String, url : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/face/byUrl")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "fileUrl" -> url))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/enrollments/face/byUrl")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "fileUrl" -> url))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def createVideoEnrollment(userId : String, lang : String, phrase : String, filePath : String) : String = {
    if (!Files.exists(Paths.get(filePath))) {
      throw new IOException("File " + filePath + " not found") 
    }
    return createVideoEnrollment(userId, lang, phrase, pathToByteArray(filePath))
  }

  def createVideoEnrollment(userId : String, lang : String, phrase : String, file : Array[Byte]) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/video")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "phrase" -> phrase))
        .postMulti(MultiPart("video", "video", "video/mp4", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/enrollments/video")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "phrase" -> phrase))
        .postMulti(MultiPart("video", "video", "video/mp4", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def createVideoEnrollmentByUrl(userId : String, lang : String, phrase : String, url : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/video/byUrl")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "fileUrl" -> url, "phrase" -> phrase))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/enrollments/video/byUrl")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "fileUrl" -> url, "phrase" -> phrase))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def deleteAllEnrollments(userId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/" + userId + "/all")
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/enrollments/" + userId + "/all")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def deleteVideoEnrollment(userId : String, enrollmentId : Int) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/video/" + userId + "/" + String.valueOf(enrollmentId))
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/enrollments/video/" + userId + "/" + String.valueOf(enrollmentId))
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def deleteAllVideoEnrollments(userId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/" + userId + "/video")
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/enrollments/" + userId + "/video")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def deleteVoiceEnrollment(userId : String, enrollmentId : Int) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/voice/" + userId + "/" + String.valueOf(enrollmentId))
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/enrollments/voice/" + userId + "/" + String.valueOf(enrollmentId))
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def deleteAllVoiceEnrollments(userId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/" + userId + "/voice")
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/enrollments/" + userId + "/voice")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def deleteFaceEnrollment(userId : String, enrollmentId : Int) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/face/" + userId + "/" + String.valueOf(enrollmentId))
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/enrollments/face/" + userId + "/" + String.valueOf(enrollmentId))
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def deleteAllFaceEnrollments(userId : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/enrollments/" + userId + "/face")
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/enrollments/" + userId + "/face")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken).method("DELETE")
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def voiceVerification(userId : String, lang : String, phrase : String, filePath : String) : String = {
    if (!Files.exists(Paths.get(filePath))) {
      throw new IOException("File " + filePath + " not found") 
    }
    return voiceVerification(userId, lang, phrase, pathToByteArray(filePath))
  }

  def voiceVerification(userId : String, lang : String, phrase : String, file : Array[Byte]) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/verification/voice")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "phrase" -> phrase))
        .postMulti(MultiPart("recording", "recording", "audio/wav", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/verification/voice")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "phrase" -> phrase))
        .postMulti(MultiPart("recording", "recording", "audio/wav", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def voiceVerificationByUrl(userId : String, lang : String, phrase : String, url : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/verification/voice/byUrl")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "fileUrl" -> url, "phrase" -> phrase))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/verification/voice/byUrl")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "fileUrl" -> url, "phrase" -> phrase))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def faceVerification(userId : String, filePath : String) : String = {
    if (!Files.exists(Paths.get(filePath))) {
      throw new IOException("File " + filePath + " not found") 
    }
    return faceVerification(userId, pathToByteArray(filePath))
  }

  def faceVerification(userId : String, file : Array[Byte]) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/verification/face")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId))
        .postMulti(MultiPart("video", "video", "video/mp4", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/verification/face")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId))
        .postMulti(MultiPart("video", "video", "video/mp4", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def faceVerificationByUrl(userId : String, url : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/verification/face/byUrl")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "fileUrl" -> url))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/verification/face/byUrl")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "fileUrl" -> url))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def videoVerification(userId : String, lang : String, phrase : String, filePath : String) : String = {
    if (!Files.exists(Paths.get(filePath))) {
      throw new IOException("File " + filePath + " not found") 
    }
    return videoVerification(userId, lang, phrase, pathToByteArray(filePath))
  }

  def videoVerification(userId : String, lang : String, phrase : String, file : Array[Byte]) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/verification/video")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "phrase" -> phrase))
        .postMulti(MultiPart("video", "video", "video/mp4", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/verification/video")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "phrase" -> phrase))
        .postMulti(MultiPart("video", "video", "video/mp4", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def videoVerificationByUrl(userId : String, lang : String, phrase : String, url : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/verification/video/byUrl")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "fileUrl" -> url, "phrase" -> phrase))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/verification/video/byUrl")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("userId" -> userId, "contentLanguage" -> lang, "fileUrl" -> url, "phrase" -> phrase))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def voiceIdentification(groupId : String, lang : String, phrase : String, filePath : String) : String = {
    if (!Files.exists(Paths.get(filePath))) {
      throw new IOException("File " + filePath + " not found") 
    }
    return voiceIdentification(groupId, lang, phrase, pathToByteArray(filePath))
  }

  def voiceIdentification(groupId : String, lang : String, phrase : String, file : Array[Byte]) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/identification/voice")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("groupId" -> groupId, "contentLanguage" -> lang, "phrase" -> phrase))
        .postMulti(MultiPart("recording", "recording", "audio/wav", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/identification/voice")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("groupId" -> groupId, "contentLanguage" -> lang, "phrase" -> phrase))
        .postMulti(MultiPart("recording", "recording", "audio/wav", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def voiceIdentificationByUrl(groupId : String, lang : String, phrase : String, url : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/identification/voice/byUrl")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("groupId" -> groupId, "contentLanguage" -> lang, "fileUrl" -> url, "phrase" -> phrase))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/identification/voice/byUrl")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("groupId" -> groupId, "contentLanguage" -> lang, "fileUrl" -> url, "phrase" -> phrase))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def videoIdentification(groupId : String, lang : String, phrase : String, filePath : String) : String = {
    if (!Files.exists(Paths.get(filePath))) {
      throw new IOException("File " + filePath + " not found") 
    }
    return videoIdentification(groupId, lang, phrase, pathToByteArray(filePath))
  }

  def videoIdentification(groupId : String, lang : String, phrase : String, file : Array[Byte]) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/identification/video")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("groupId" -> groupId, "contentLanguage" -> lang, "phrase" -> phrase))
        .postMulti(MultiPart("video", "video", "video/mp4", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/identification/video")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("groupId" -> groupId, "contentLanguage" -> lang, "phrase" -> phrase))
        .postMulti(MultiPart("video", "video", "video/mp4", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def videoIdentificationByUrl(groupId : String, lang : String, phrase : String, url : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/identification/video/byUrl")
        .headers(header).auth(apikey, apitoken).postForm(Seq("groupId" -> groupId, "contentLanguage" -> lang, "fileUrl" -> url, "phrase" -> phrase))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/identification/video/byUrl")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken).postForm(Seq("groupId" -> groupId, "contentLanguage" -> lang, "fileUrl" -> url, "phrase" -> phrase))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def faceIdentification(groupId : String, filePath : String) : String = {
    if (!Files.exists(Paths.get(filePath))) {
      throw new IOException("File " + filePath + " not found") 
    }
    if (notificationUrl == "") {
      return faceIdentification(groupId, pathToByteArray(filePath))
    } else {
      return faceIdentification(groupId, pathToByteArray(filePath))
    }
  }

  def faceIdentification(groupId : String, file : Array[Byte]) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/identification/face")
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("groupId" -> groupId))
        .postMulti(MultiPart("video", "video", "video/mp4", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/identification/face")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken)
        .postForm(Seq("groupId" -> groupId))
        .postMulti(MultiPart("video", "video", "video/mp4", file))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def faceIdentificationByUrl(groupId : String, url : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/identification/face/byUrl")
        .headers(header).auth(apikey, apitoken).postForm(Seq("groupId" -> groupId,"fileUrl" -> url))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    } else {
      return Http(baseUrl + "/identification/face/byUrl")
        .param("notificationURL", notificationUrl)
        .headers(header).auth(apikey, apitoken).postForm(Seq("groupId" -> groupId,"fileUrl" -> url))
        .timeout(connTimeoutMs = connTimeoutMs, readTimeoutMs = readTimeoutMs)
        .asString.body
    }
  }

  def getPhrases(contentLanguage : String) : String = {
    if (notificationUrl == "") {
      return Http(baseUrl + "/phrases/" + contentLanguage).headers(header).auth(apikey, apitoken).asString.body
    } else {
      return Http(baseUrl + "/phrases/" + contentLanguage).param("notificationURL", notificationUrl).headers(header).auth(apikey, apitoken).asString.body
    }
  }

  def createUserToken(userId : String, secondsToTimeout : Int) : String = {
    return Http(baseUrl + "/users/" + userId + "/token").param("timeOut", String.valueOf(secondsToTimeout)).headers(header).auth(apikey, apitoken).postMulti().asString.body
  }

}
