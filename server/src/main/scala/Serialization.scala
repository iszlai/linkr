import argonaut._, Argonaut._

object Serialization {
  implicit def PersonCodecJson: CodecJson[Person] =
    casecodec2(Person.apply,Person.unapply)("name", "age")
}
