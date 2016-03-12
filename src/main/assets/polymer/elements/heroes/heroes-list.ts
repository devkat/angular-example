/// <reference path="../../../../../../target/web/web-modules/main/webjars/lib/polymer-ts/polymer-ts.d.ts" />
/// <reference path="../../model/hero.ts" />

//import { Hero } from "../../model/hero";

@component("heroes-list")
class HeroesList extends polymer.Base {

  @property({ type: Array, notify: true })
  heroes: Hero[];

  ready() {
    this.heroes = [ { id: 1, name: "Storm" } ];
  }

}

HeroesList.register();