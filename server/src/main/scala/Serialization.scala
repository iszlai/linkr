import spray.json.DefaultJsonProtocol

object MyJsonProtocol extends DefaultJsonProtocol {
  implicit val personFormat = jsonFormat2(Person)
  implicit val userFormat = jsonFormat2(User)

}
