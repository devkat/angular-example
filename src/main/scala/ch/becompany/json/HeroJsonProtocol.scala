package ch.becompany.json

import ch.becompany.model.Hero
import spray.json.DefaultJsonProtocol

object HeroJsonProtocol extends DefaultJsonProtocol {

  implicit val HeroFormat = jsonFormat2(Hero)

}
