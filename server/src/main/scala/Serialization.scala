import argonaut._, Argonaut._

object Serialization {
  implicit def UserCodecJson: CodecJson[UserDTO] =
    casecodec2(UserDTO.apply,UserDTO.unapply)("name", "password")
}
