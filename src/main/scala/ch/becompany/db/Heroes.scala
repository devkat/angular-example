package ch.becompany.db

import ch.becompany.Boot
import slick.driver.H2Driver.api._
import ch.becompany.model.Hero
import ch.becompany.db.Db._
import ch.becompany.dao.Tables.{Hero => HeroDao, HeroRow}
import scala.concurrent.{ExecutionContext, Future}

object Heroes {

  def toHero: HeroRow => Hero = {
    case HeroRow(id, name) => Hero(id, name)
  }

  def find(id: Long)(implicit ex: ExecutionContext): Future[Option[Hero]] = {
    db.run(HeroDao.filter(_.id === id).result.headOption map (_ map toHero))
  }

  def list()(implicit ex: ExecutionContext): Future[Seq[Hero]] = {
    db.run(HeroDao.result) map (_ map toHero)
  }

  def add(hero: Hero) = {
    val query = HeroDao.map(h => h.name) returning
      HeroDao += hero.name
    db.run(query)
  }

  def update(id: Long, hero: Hero) = {
    val query = (for {
      h <- HeroDao if h.id === id
    } yield {
      h.name
    }).update(hero.name)
    db.run(query)
  }
}