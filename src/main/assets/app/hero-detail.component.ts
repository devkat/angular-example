import {Component, OnInit} from 'angular2/core';
import {RouteParams} from 'angular2/router';

import {Hero} from './hero';
import {HeroService} from './hero.service';


@Component({
  selector: 'my-hero-detail',
  inputs: ['hero'],
  templateUrl: 'app/hero-detail.component.html',
  styleUrls: [ 'app/hero-detail.component.css' ]
})

export class HeroDetailComponent {
  hero: Hero;

  constructor(
    private _heroService: HeroService,
    private _routeParams: RouteParams) {
  }

  ngOnInit() {
    let id = +this._routeParams.get('id');
    if (id) {
      this._heroService.getHero(id).subscribe(
        hero => this.hero = hero,
        error => console.log(error)
      )
    } else {
      this.hero = {
        id: null,
        name: ""
      };
    }
  }

  save() {
    if (this.hero.id === null) {
      this._heroService.addHero(this.hero.name).subscribe(
        hero  => this.hero = hero,
        error =>  console.log("Error: " + error)
      );
    }
  }

  goBack() {
    window.history.back();
  }
}