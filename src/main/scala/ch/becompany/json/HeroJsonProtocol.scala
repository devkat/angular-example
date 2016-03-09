package ch.becompany.json

import ch.becompany.model.Hero
import spray.json.{RootJsonFormat, JsValue, DefaultJsonProtocol}

object HeroJsonProtocol extends DefaultJsonProtocol {

  implicit val HeroFormat = jsonFormat2(Hero)

  implicit val jsValueFormat = new RootJsonFormat[JsValue] {
    def write(value: JsValue) = value
    def read(value: JsValue) = value
  }

}
