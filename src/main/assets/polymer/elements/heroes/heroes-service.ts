/// <reference path="../../../../../../target/web/web-modules/main/webjars/lib/polymer-ts/polymer-ts.d.ts" />
/// <reference path="../../model/hero.ts" />

//import { Hero } from "../../model/hero";

@component("heroes-service")
class HeroesService extends polymer.Base {

  ready() {
    this.$.ajax.generateRequest();
  }

  heroesLoaded(event: any): void {
    console.log("Response", event.detail.response);
    this.fire("heroesLoaded", event.detail.response);
  }

}

HeroesService.register();