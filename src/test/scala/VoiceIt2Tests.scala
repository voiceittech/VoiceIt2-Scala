package test

import sys.process._
import org.apache.commons.io.FileUtils
import java.io.IOException
import java.net.URL
import java.io.File
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import play.api.libs.json.Json
import voiceit2.VoiceIt2

class TestIO extends FunSuite with BeforeAndAfter {

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _
    test("createVideoEnrollment()") {
      intercept[IOException] {
        vi.createVideoEnrollment(userId, "", "", "not_a_real_file")
      }
    }

    test("createVoiceEnrollment()") {
      intercept[IOException] {
        vi.createVoiceEnrollment(userId, "", "", "not_a_real_file")
      }
    }

    test("createFaceEnrollment()") {
      intercept[IOException] {
        vi.createFaceEnrollment(userId, "not_a_real_file")
      }
    }

    test("videoVerification()") {
      intercept[IOException] {
        vi.videoVerification(userId, "", "", "not_a_real_file")
      }

    }

    test("voiceVerification()") {
      intercept[IOException] {
        vi.voiceVerification(userId, "", "", "not_a_real_file")
      }
    }

    test("faceVerification()") {
      intercept[IOException] {
        vi.faceVerification(userId, "not_a_real_file")
      }
    }

    test("videoIdentification()") {
      intercept[IOException] {
        vi.videoIdentification(userId, "", "", "not_a_real_file")
      }
    }

    test("voiceIdentification()") {
      intercept[IOException] {
        vi.voiceIdentification(userId, "", "", "not_a_real_file")
      }
    }

    test("faceIdentification()") {
      intercept[IOException] {
        vi.faceIdentification(userId, "not_a_real_file")
      }
    }
}

/*
class TestBasics extends FunSuite with BeforeAndAfter {
    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _

    test("createUser()") {
      val ret = Json.parse(vi.createUser)
      val status = (ret \ "status").get.as[Int]
      assert(status === 201)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
      userId = (ret \ "userId").get.as[String]
    }

    test("getAllUsers()") {
      val ret = Json.parse(vi.getAllUsers)
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    test("checkUserExists()") {
      val ret = Json.parse(vi.checkUserExists(userId))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    var groupId : String = _
    test("createGroup()") {
      val ret = Json.parse(vi.createGroup("Sample Group Description"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 201)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
      groupId = (ret \ "groupId").get.as[String]
    }

    test("getAllGroups()") {
      val ret = Json.parse(vi.getAllGroups)
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    test("getGroup()") {
      val ret = Json.parse(vi.getGroup(groupId))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    test("checkGroupExists()") {
      val ret = Json.parse(vi.checkGroupExists(groupId))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    test("addUserToGroup()") {
      val ret = Json.parse(vi.addUserToGroup(groupId, userId))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    test("getGroupsForUser()") {
      val ret = Json.parse(vi.getGroupsForUser(userId))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    test("removeUserFromGroup()") {
      val ret = Json.parse(vi.removeUserFromGroup(groupId, userId))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    test("deleteUser()") {
      val ret = Json.parse(vi.deleteUser(userId))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    test("deleteGroup()") {
      val ret = Json.parse(vi.deleteGroup(groupId))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    test("getPhrases()") {
      val ret = Json.parse(vi.getPhrases("en-US"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }
}

class TestGetVideoEnrollments extends FunSuite with BeforeAndAfter {
    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _

    before {
      var ret = Json.parse(vi.createUser)
      userId = (ret \ "userId").get.as[String]
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan1.mov", "./testgetenrollmentvideoEnrollmentArmaan1.mov")
      vi.createVideoEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testgetenrollmentvideoEnrollmentArmaan1.mov")
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId)
      vi.deleteUser(userId)
      FileUtils.deleteQuietly(new File("./testgetenrollmentvideoEnrollmentArmaan1.mov"))
    }


    test("getVideoEnrollments()") {
      val ret = Json.parse(vi.getVideoEnrollments(userId))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

}

class TestGetFaceEnrollments extends FunSuite with BeforeAndAfter {
    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _

    before {
      var ret = Json.parse(vi.createUser)
      userId = (ret \ "userId").get.as[String]
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan1.mp4", "./testgetenrollmentfaceEnrollmentArmaan1.mp4")
      vi.createFaceEnrollment(userId, "./testgetenrollmentfaceEnrollmentArmaan1.mp4")
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId)
      vi.deleteUser(userId)
      FileUtils.deleteQuietly(new File("./testgetenrollmentfaceEnrollmentArmaan1.mp4"))
    }


    test("getFaceEnrollments()") {
      val ret = Json.parse(vi.getFaceEnrollments(userId))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

}

class TestGetVoiceEnrollments extends FunSuite with BeforeAndAfter {
    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _

    before {
      var ret = Json.parse(vi.createUser)
      userId = (ret \ "userId").get.as[String]
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan1.wav", "./testgetenrollmentenrollmentArmaan1.wav")
      vi.createVoiceEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testgetenrollmentenrollmentArmaan1.wav")
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId)
      vi.deleteUser(userId)
      FileUtils.deleteQuietly(new File("./testgetenrollmentenrollmentArmaan1.wav"))
    }


    test("getVoiceEnrollments()") {
      val ret = Json.parse(vi.getVoiceEnrollments(userId))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

}

class TestDeleteEnrollment extends FunSuite with BeforeAndAfter {

    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _
    var videoEnrollmentId : Int = _
    var faceEnrollmentId : Int = _
    var voiceEnrollmentId : Int = _

    before {
      var ret = Json.parse(vi.createUser)
      userId = (ret \ "userId").get.as[String]
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan1.mov", "./testdeleteenrollmentvideoEnrollmentArmaan1.mov")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan2.mov", "./testdeleteenrollmentvideoEnrollmentArmaan2.mov")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan1.wav", "./testdeleteenrollmentenrollmentArmaan1.wav")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan2.wav", "./testdeleteenrollmentenrollmentArmaan2.wav")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan1.mp4", "./testdeleteenrollmentfaceEnrollmentArmaan1.mp4")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan2.mp4", "./testdeleteenrollmentfaceEnrollmentArmaan2.mp4")
      ret = Json.parse(vi.createVideoEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testdeleteenrollmentvideoEnrollmentArmaan1.mov"))
      videoEnrollmentId = (ret \ "id").get.as[Int]
      vi.createVideoEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testdeleteenrollmentvideoEnrollmentArmaan2.mov")
      ret = Json.parse(vi.createVoiceEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testdeleteenrollmentenrollmentArmaan1.wav"))
      voiceEnrollmentId = (ret \ "id").get.as[Int]
      vi.createVoiceEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testdeleteenrollmentenrollmentArmaan2.wav")
      ret = Json.parse(vi.createFaceEnrollment(userId, "./testdeleteenrollmentfaceEnrollmentArmaan1.mp4"))
      faceEnrollmentId = (ret \ "faceEnrollmentId").get.as[Int]
      vi.createFaceEnrollment(userId, "./testdeleteenrollmentfaceEnrollmentArmaan2.mp4")
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId)
      vi.deleteUser(userId)
      FileUtils.deleteQuietly(new File("./testdeleteenrollmentvideoEnrollmentArmaan1.mov"))
      FileUtils.deleteQuietly(new File("./testdeleteenrollmentvideoEnrollmentArmaan2.mov"))
      FileUtils.deleteQuietly(new File("./testdeleteenrollmentfaceEnrollmentArmaan1.mp4"))
      FileUtils.deleteQuietly(new File("./testdeleteenrollmentfaceEnrollmentArmaan2.mp4"))
      FileUtils.deleteQuietly(new File("./testdeleteenrollmentenrollmentArmaan1.wav"))
      FileUtils.deleteQuietly(new File("./testdeleteenrollmentenrollmentArmaan2.wav"))
    }

    // Delete Video Enrollment for User
    test("deleteVideoEnrollment()") {
      vi.deleteVideoEnrollment(userId, videoEnrollmentId)
      val ret = Json.parse(vi.getVideoEnrollments(userId))
      val count = (ret \ "count").get.as[Int]
      assert(count === 1)
    }

    // Delete Voice Enrollment for User
    test("deleteVoiceEnrollment()") {
      vi.deleteVoiceEnrollment(userId, voiceEnrollmentId)
      val ret = Json.parse(vi.getVoiceEnrollments(userId))
      val count = (ret \ "count").get.as[Int]
      assert(count === 1)
    }

    // Delete Face Enrollment for User
    // test("deleteFaceEnrollment()") {
      // vi.deleteFaceEnrollment(userId, faceEnrollmentId)
      // val ret = Json.parse(vi.getFaceEnrollments(userId))
      // val count = (ret \ "count").get.as[Int]
      // assert(count === 1)
    // }
}

class TestDeleteEnrollments extends FunSuite with BeforeAndAfter {

    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _

    before {
      var ret = Json.parse(vi.createUser)
      userId = (ret \ "userId").get.as[String]
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan1.mov", "./testdeleteenrollmentsvideoEnrollmentArmaan1.mov")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan2.mov", "./testdeleteenrollmentsvideoEnrollmentArmaan2.mov")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan1.wav", "./testdeleteenrollmentsenrollmentArmaan1.wav")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan2.wav", "./testdeleteenrollmentsenrollmentArmaan2.wav")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan1.mp4", "./testdeleteenrollmentsfaceEnrollmentArmaan1.mp4")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan2.mp4", "./testdeleteenrollmentsfaceEnrollmentArmaan2.mp4")

      vi.createVideoEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testdeleteenrollmentsvideoEnrollmentArmaan1.mov")
      vi.createVideoEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testdeleteenrollmentsvideoEnrollmentArmaan2.mov")

      vi.createVoiceEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testdeleteenrollmentsenrollmentArmaan1.wav")
      vi.createVoiceEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testdeleteenrollmentsenrollmentArmaan2.wav")

      vi.createFaceEnrollment(userId, "./testdeleteenrollmentsfaceEnrollmentArmaan1.mp4")
      vi.createFaceEnrollment(userId, "./testdeleteenrollmentsfaceEnrollmentArmaan2.mp4")
    }

    after {
      vi.deleteUser(userId)
      FileUtils.deleteQuietly(new File("./testdeleteenrollmentsvideoEnrollmentArmaan1.mov"))
      FileUtils.deleteQuietly(new File("./testdeleteenrollmentsvideoEnrollmentArmaan2.mov"))
      FileUtils.deleteQuietly(new File("./testdeleteenrollmentsenrollmentArmaan1.wav"))
      FileUtils.deleteQuietly(new File("./testdeleteenrollmentsenrollmentArmaan2.wav"))
      FileUtils.deleteQuietly(new File("./testdeleteenrollmentsfaceEnrollmentArmaan1.mp4"))
      FileUtils.deleteQuietly(new File("./testdeleteenrollmentsfaceEnrollmentArmaan2.mp4"))
    }

    // Delete All Video Enrollments for User
    test("deleteAllVideoEnrollments()") {
      vi.deleteAllVideoEnrollments(userId)
      val ret = Json.parse(vi.getVideoEnrollments(userId))
      val count = (ret \ "count").get.as[Int]
      assert(count === 0)
    }

    // Delete All Voice Enrollments for User
    test("deleteAllVoiceEnrollments()") {
      vi.deleteAllVoiceEnrollments(userId)
      val ret = Json.parse(vi.getVoiceEnrollments(userId))
      val count = (ret \ "count").get.as[Int]
      assert(count === 0)
    }

    // Delete All Face Enrollments for User
    test("deleteAllFaceEnrollments()") {
      vi.deleteAllFaceEnrollments(userId)
      val ret = Json.parse(vi.getFaceEnrollments(userId))
      val count = (ret \ "count").get.as[Int]
      assert(count === 0)
    }

}

class TestDeleteAllEnrollments extends FunSuite with BeforeAndAfter {

    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _

    before {
      var ret = Json.parse(vi.createUser)
      userId = (ret \ "userId").get.as[String]
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan1.mov", "./testdeleteallenrollmentsvideoEnrollmentArmaan1.mov")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan2.mov", "./testdeleteallenrollmentsvideoEnrollmentArmaan2.mov")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan1.wav", "./testdeleteallenrollmentsenrollmentArmaan1.wav")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan2.wav", "./testdeleteallenrollmentsenrollmentArmaan2.wav")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan1.mp4", "./testdeleteallenrollmentsfaceEnrollmentArmaan1.mp4")
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan2.mp4", "./testdeleteallenrollmentsfaceEnrollmentArmaan2.mp4")

      vi.createVideoEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testdeleteallenrollmentsvideoEnrollmentArmaan1.mov")
      vi.createVideoEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testdeleteallenrollmentsvideoEnrollmentArmaan2.mov")

      vi.createVoiceEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testdeleteallenrollmentsenrollmentArmaan1.wav")
      vi.createVoiceEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testdeleteallenrollmentsenrollmentArmaan2.wav")

      vi.createFaceEnrollment(userId, "./testdeleteallenrollmentsfaceEnrollmentArmaan1.mp4")
      vi.createFaceEnrollment(userId, "./testdeleteallenrollmentsfaceEnrollmentArmaan2.mp4")
    }

    after {
      vi.deleteUser(userId)
      FileUtils.deleteQuietly(new File("./testdeleteallenrollmentsvideoEnrollmentArmaan1.mov"))
      FileUtils.deleteQuietly(new File("./testdeleteallenrollmentsvideoEnrollmentArmaan2.mov"))
      FileUtils.deleteQuietly(new File("./testdeleteallenrollmentsenrollmentArmaan1.wav"))
      FileUtils.deleteQuietly(new File("./testdeleteallenrollmentsenrollmentArmaan2.wav"))
      FileUtils.deleteQuietly(new File("./testdeleteallenrollmentsfaceEnrollmentArmaan1.mp4"))
      FileUtils.deleteQuietly(new File("./testdeleteallenrollmentsfaceEnrollmentArmaan2.mp4"))
    }

    // Delete All Enrollments for User
    test("deleteAllEnrollmentsForUser()") {
      vi.deleteAllEnrollmentsForUser(userId)
      var ret = Json.parse(vi.getVideoEnrollments(userId))
      var count = (ret \ "count").get.as[Int]
      assert(count === 0)
      ret = Json.parse(vi.getVoiceEnrollments(userId))
      count = (ret \ "count").get.as[Int]
      assert(count === 0)
      ret = Json.parse(vi.getFaceEnrollments(userId))
      count = (ret \ "count").get.as[Int]
      assert(count === 0)
    }
}


class TestVideoEnrollments extends FunSuite with BeforeAndAfter {

    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _


    before {
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan1.mov", "./testenrollmentvideoEnrollmentArmaan1.mov")
      var ret = Json.parse(vi.createUser)
      userId = (ret \ "userId").get.as[String]
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId)
      vi.deleteUser(userId)
      FileUtils.deleteQuietly(new File("./testenrollmentvideoEnrollmentArmaan1.mov"))
    }

    // Create Enrollments

    test("createVideoEnrollment()") {
      val ret = Json.parse(vi.createVideoEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testenrollmentvideoEnrollmentArmaan1.mov"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 201)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }
}

class TestVideoVerificationIdentification extends FunSuite with BeforeAndAfter {
    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId1 : String = _
    var userId2 : String = _
    var groupId : String = _

    before {
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoVerificationArmaan1.mov", "./videoVerificationArmaan1.mov")
      var ret = Json.parse(vi.createUser)
      userId1 = (ret \ "userId").get.as[String]
      ret = Json.parse(vi.createUser)
      userId2 = (ret \ "userId").get.as[String]
      ret = Json.parse(vi.createGroup("Sample Group Description"))
      groupId = (ret \ "groupId").get.as[String]
      vi.addUserToGroup(groupId, userId1)
      vi.addUserToGroup(groupId, userId2)
      vi.createVideoEnrollmentByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan1.mov")
      vi.createVideoEnrollmentByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan2.mov")
      vi.createVideoEnrollmentByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan3.mov")
      vi.createVideoEnrollmentByUrl(userId2, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentStephen1.mov")
      vi.createVideoEnrollmentByUrl(userId2, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentStephen2.mov")
      vi.createVideoEnrollmentByUrl(userId2, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentStephen3.mov")
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId1)
      vi.deleteAllEnrollmentsForUser(userId2)
      vi.deleteUser(userId1)
      vi.deleteUser(userId2)
      vi.deleteUser(groupId)
      FileUtils.deleteQuietly(new File("./videoVerificationArmaan1.mov"))
    }

    // Video Verification
    test("videoVerification()") {
      val ret = Json.parse(vi.videoVerification(userId1, "en-US", "never forget tomorrow is a new day", "./videoVerificationArmaan1.mov"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    // Video Identification
    test("videoIdentification()") {
      val ret = Json.parse(vi.videoIdentification(groupId, "en-US", "never forget tomorrow is a new day", "./videoVerificationArmaan1.mov"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
      val userId = (ret \ "userId").get.as[String]
      assert(userId === userId1)
    }
}

class TestVideoEnrollmentsByUrl extends FunSuite with BeforeAndAfter {

    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _

    before {
      var ret = Json.parse(vi.createUser)
      userId = (ret \ "userId").get.as[String]
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId)
      vi.deleteUser(userId)
    }

    // Create Enrollments

    test("createVideoEnrollmentByUrl()") {
      val ret = Json.parse(vi.createVideoEnrollmentByUrl(userId, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan1.mov"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 201)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }
}
class TestVideoVerificationIdentificationByUrl extends FunSuite with BeforeAndAfter {

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId1 : String = _
    var userId2 : String = _
    var groupId : String = _

    before {
      var ret = Json.parse(vi.createUser)
      userId1 = (ret \ "userId").get.as[String]
      ret = Json.parse(vi.createUser)
      userId2 = (ret \ "userId").get.as[String]
      ret = Json.parse(vi.createGroup("Sample Group Description"))
      groupId = (ret \ "groupId").get.as[String]
      vi.addUserToGroup(groupId, userId1)
      vi.addUserToGroup(groupId, userId2)
      vi.createVideoEnrollmentByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan1.mov")
      vi.createVideoEnrollmentByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan2.mov")
      vi.createVideoEnrollmentByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentArmaan3.mov")
      vi.createVideoEnrollmentByUrl(userId2, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentStephen1.mov")
      vi.createVideoEnrollmentByUrl(userId2, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentStephen2.mov")
      vi.createVideoEnrollmentByUrl(userId2, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoEnrollmentStephen3.mov")
    }

    
    after {
      vi.deleteAllEnrollmentsForUser(userId1)
      vi.deleteAllEnrollmentsForUser(userId2)
      vi.deleteUser(userId1)
      vi.deleteUser(userId2)
      vi.deleteUser(groupId)
    }

    // Video Verification
    test("videoVerificationByUrl()") {
      val ret = Json.parse(vi.videoVerificationByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoVerificationArmaan1.mov"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    // Video Identification
    test("videoIdentificationByUrl()") {
      val ret = Json.parse(vi.videoIdentificationByUrl(groupId, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/videoVerificationArmaan1.mov"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
      val userId = (ret \ "userId").get.as[String]
      assert(userId === userId1)
    }
}


class TestVoiceEnrollments extends FunSuite with BeforeAndAfter {

    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _

    before {
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan1.wav", "./testenrollmentenrollmentArmaan1.wav")
      var ret = Json.parse(vi.createUser)
      userId = (ret \ "userId").get.as[String]
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId)
      vi.deleteUser(userId)
      FileUtils.deleteQuietly(new File("./testenrollmentenrollmentArmaan1.wav"))
    }

    // Create Enrollments

    test("createVoiceEnrollment()") {
      val ret = Json.parse(vi.createVoiceEnrollment(userId, "en-US", "never forget tomorrow is a new day", "./testenrollmentenrollmentArmaan1.wav"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 201)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }
}

class TestVoiceEnrollmentsByUrl extends FunSuite with BeforeAndAfter {

    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _

    before {
      var ret = Json.parse(vi.createUser)
      userId = (ret \ "userId").get.as[String]
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId)
      vi.deleteUser(userId)
    }

    // Create Enrollments

    test("createVoiceEnrollmentByUrl()") {
      val ret = Json.parse(vi.createVoiceEnrollmentByUrl(userId, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan1.wav"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 201)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }
}

class TestVoiceVerificationIdentification extends FunSuite with BeforeAndAfter {
    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId1 : String = _
    var userId2 : String = _
    var groupId : String = _

    before {
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/verificationArmaan1.wav", "./verificationArmaan1.wav")
      var ret = Json.parse(vi.createUser)
      userId1 = (ret \ "userId").get.as[String]
      ret = Json.parse(vi.createUser)
      userId2 = (ret \ "userId").get.as[String]
      ret = Json.parse(vi.createGroup("Sample Group Description"))
      groupId = (ret \ "groupId").get.as[String]
      vi.addUserToGroup(groupId, userId1)
      vi.addUserToGroup(groupId, userId2)
      vi.createVoiceEnrollmentByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan1.wav")
      vi.createVoiceEnrollmentByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan2.wav")
      vi.createVoiceEnrollmentByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan3.wav")
      vi.createVoiceEnrollmentByUrl(userId2, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentStephen1.wav")
      vi.createVoiceEnrollmentByUrl(userId2, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentStephen2.wav")
      vi.createVoiceEnrollmentByUrl(userId2, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentStephen3.wav")
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId1)
      vi.deleteAllEnrollmentsForUser(userId2)
      vi.deleteUser(userId1)
      vi.deleteUser(userId2)
      vi.deleteUser(groupId)
      FileUtils.deleteQuietly(new File("./verificationArmaan1.wav"))
    }

    // Voice Verification
    test("voiceVerification()") {
      val ret = Json.parse(vi.voiceVerification(userId1, "en-US", "never forget tomorrow is a new day", "./verificationArmaan1.wav"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    // Voice Identification
    test("voiceIdentification()") {
      val ret = Json.parse(vi.voiceIdentification(groupId, "en-US", "never forget tomorrow is a new day", "./verificationArmaan1.wav"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
      val userId = (ret \ "userId").get.as[String]
      assert(userId === userId1)
    }
}

class TestVoiceVerificationIdentificationByUrl extends FunSuite with BeforeAndAfter {
    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId1 : String = _
    var userId2 : String = _
    var groupId : String = _

    before {
      var ret = Json.parse(vi.createUser)
      userId1 = (ret \ "userId").get.as[String]
      ret = Json.parse(vi.createUser)
      userId2 = (ret \ "userId").get.as[String]
      ret = Json.parse(vi.createGroup("Sample Group Description"))
      groupId = (ret \ "groupId").get.as[String]
      vi.addUserToGroup(groupId, userId1)
      vi.addUserToGroup(groupId, userId2)
      vi.createVoiceEnrollmentByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan1.wav")
      vi.createVoiceEnrollmentByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan2.wav")
      vi.createVoiceEnrollmentByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentArmaan3.wav")
      vi.createVoiceEnrollmentByUrl(userId2, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentStephen1.wav")
      vi.createVoiceEnrollmentByUrl(userId2, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentStephen2.wav")
      vi.createVoiceEnrollmentByUrl(userId2, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/enrollmentStephen3.wav")
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId1)
      vi.deleteAllEnrollmentsForUser(userId2)
      vi.deleteUser(userId1)
      vi.deleteUser(userId2)
      vi.deleteUser(groupId)
    }

      
    // Voice Verification By Url
    test("voiceVerificationByUrl()") {
      val ret = Json.parse(vi.voiceVerificationByUrl(userId1, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/verificationArmaan1.wav"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    // Voice Identification By Url
    test("voiceIdentificationByUrl()") {
      val ret = Json.parse(vi.voiceIdentificationByUrl(groupId, "en-US", "never forget tomorrow is a new day", "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/verificationArmaan1.wav"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
      val userId = (ret \ "userId").get.as[String]
      assert(userId === userId1)
    }
}

class TestFaceEnrollments extends FunSuite with BeforeAndAfter {

    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _

    before {
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan1.mp4", "./testenrollmentfaceEnrollmentArmaan1.mp4")
      var ret = Json.parse(vi.createUser)
      userId = (ret \ "userId").get.as[String]
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId)
      vi.deleteUser(userId)
      FileUtils.deleteQuietly(new File("./testenrollmentfaceEnrollmentArmaan1.mp4"))
    }

    // Create Enrollments

    test("createFaceEnrollment()") {
      val ret = Json.parse(vi.createFaceEnrollment(userId, "./testenrollmentfaceEnrollmentArmaan1.mp4"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 201)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }
}

class TestFaceEnrollmentsByUrl extends FunSuite with BeforeAndAfter {

    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId : String = _

    before {
      var ret = Json.parse(vi.createUser)
      userId = (ret \ "userId").get.as[String]
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId)
      vi.deleteUser(userId)
    }

    // Create Enrollments

    test("createFaceEnrollmentByUrl()") {
      val ret = Json.parse(vi.createFaceEnrollmentByUrl(userId, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan1.mp4"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 201)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }
}

class TestFaceVerificationIdentification extends FunSuite with BeforeAndAfter {
    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId1 : String = _
    var userId2 : String = _
    var groupId : String = _

    before {
      downloadFile("https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceVerificationArmaan1.mp4", "./faceVerificationArmaan1.mp4")
      var ret = Json.parse(vi.createUser)
      userId1 = (ret \ "userId").get.as[String]
      ret = Json.parse(vi.createUser)
      userId2 = (ret \ "userId").get.as[String]
      ret = Json.parse(vi.createGroup("Sample Group Description"))
      groupId = (ret \ "groupId").get.as[String]
      vi.addUserToGroup(groupId, userId1)
      vi.addUserToGroup(groupId, userId2)
      vi.createFaceEnrollmentByUrl(userId1, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan1.mp4")
      vi.createFaceEnrollmentByUrl(userId1, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan2.mp4")
      vi.createFaceEnrollmentByUrl(userId1, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan3.mp4")
      vi.createFaceEnrollmentByUrl(userId2, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentStephen1.mp4")
      vi.createFaceEnrollmentByUrl(userId2, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentStephen2.mp4")
      vi.createFaceEnrollmentByUrl(userId2, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentStephen3.mp4")
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId1)
      vi.deleteAllEnrollmentsForUser(userId2)
      vi.deleteUser(userId1)
      vi.deleteUser(userId2)
      vi.deleteUser(groupId)
      FileUtils.deleteQuietly(new File("./faceVerificationArmaan1.mp4"))
    }

    // Face Verification
    test("faceVerification()") {
      val ret = Json.parse(vi.faceVerification(userId1, "./faceVerificationArmaan1.mp4"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    // Face Identification
    test("faceIdentification()") {
      val ret = Json.parse(vi.faceIdentification(groupId, "./faceVerificationArmaan1.mp4"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
      val userId = (ret \ "userId").get.as[String]
      assert(userId === userId1)
    }
}

class TestFaceVerificationIdentificationByUrl extends FunSuite with BeforeAndAfter {
    def downloadFile(source : String, path : String) {
      new URL(source) #> new File(path) !!
    }

    val viapikey = sys.env("VIAPIKEY")
    val viapitoken = sys.env("VIAPITOKEN")
    var vi = new VoiceIt2(viapikey, viapitoken)
    var userId1 : String = _
    var userId2 : String = _
    var groupId : String = _

    before {
      var ret = Json.parse(vi.createUser)
      userId1 = (ret \ "userId").get.as[String]
      ret = Json.parse(vi.createUser)
      userId2 = (ret \ "userId").get.as[String]
      ret = Json.parse(vi.createGroup("Sample Group Description"))
      groupId = (ret \ "groupId").get.as[String]
      vi.addUserToGroup(groupId, userId1)
      vi.addUserToGroup(groupId, userId2)
      vi.createFaceEnrollmentByUrl(userId1, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan1.mp4")
      vi.createFaceEnrollmentByUrl(userId1, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan2.mp4")
      vi.createFaceEnrollmentByUrl(userId1, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentArmaan3.mp4")
      vi.createFaceEnrollmentByUrl(userId2, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentStephen1.mp4")
      vi.createFaceEnrollmentByUrl(userId2, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentStephen2.mp4")
      vi.createFaceEnrollmentByUrl(userId2, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceEnrollmentStephen3.mp4")
    }

    after {
      vi.deleteAllEnrollmentsForUser(userId1)
      vi.deleteAllEnrollmentsForUser(userId2)
      vi.deleteUser(userId1)
      vi.deleteUser(userId2)
      vi.deleteUser(groupId)
    }

      
    // Video Verification
    test("faceVerificationByUrl()") {
      val ret = Json.parse(vi.faceVerificationByUrl(userId1, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceVerificationArmaan1.mp4"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
    }

    // Video Identification
    test("faceIdentificationByUrl()") {
      val ret = Json.parse(vi.faceIdentificationByUrl(groupId, "https://s3.amazonaws.com/voiceit-api2-testing-files/test-data/faceVerificationArmaan1.mp4"))
      val status = (ret \ "status").get.as[Int]
      assert(status === 200)
      val responseCode = (ret \ "responseCode").get.as[String]
      assert(responseCode === "SUCC")
      val userId = (ret \ "userId").get.as[String]
      assert(userId === userId1)
    }
}
*/
