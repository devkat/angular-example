package ch.becompany.model

import ch.becompany.validation.CoreRules._
import ch.becompany.validation.Validator
import ch.becompany.validation.Validation.ValidationRule

case class Hero(id: Option[Long], name: String)

object HeroValidator extends Validator[Hero] {
  import ch.becompany.validation.CoreRules._

  val rules: Map[Option[String], Seq[ValidationRule[Hero]]] =
    Map(Some("name") -> Seq(((hero: Hero) => notBlank(hero.name), "Please enter a name.")))

}