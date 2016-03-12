/// <reference path="../../../../../../target/web/web-modules/main/webjars/lib/polymer-ts/polymer-ts.d.ts" />
/// <reference path="../../model/hero.ts" />

//import { Hero } from "../../model/hero";

@component("hero-detail")
class HeroDetail extends polymer.Base {

  @property({ type: Object, notify: true })
  hero: Hero = { id: 0, name: "Magneto" };

}

HeroDetail.register();