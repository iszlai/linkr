package io.linkr.data

import argonaut.Argonaut._
import argonaut._

object Serialization {
  implicit def UserCodecJson: CodecJson[UserDTO] =
    casecodec2(UserDTO.apply, UserDTO.unapply)("name", "password")
}
