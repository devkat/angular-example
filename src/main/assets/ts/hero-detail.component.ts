import {Component, OnInit} from '../../../../node_modules/angular2/core.d.ts';
import {RouteParams} from '../../../../node_modules/angular2/router.d.ts';

import {Hero} from './hero';
import {HeroService} from './hero.service.ts';


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
    this._heroService.getHero(id)
      .then(hero => this.hero = hero);
  }

  goBack() {
    window.history.back();
  }
}