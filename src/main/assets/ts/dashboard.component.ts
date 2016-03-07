import { Component, OnInit } from '../../../../node_modules/angular2/core.d.ts';
import { Router } from '../../../../node_modules/angular2/router.d.ts';

import { Hero } from './hero';
import { HeroService } from './hero.service.ts';

@Component({
  selector: 'my-dashboard',
  templateUrl: 'app/dashboard.component.html',
  styleUrls: ['app/dashboard.component.css']
})

export class DashboardComponent implements OnInit {

  heroes: Hero[] = [];

  constructor(
    private _router: Router,
    private _heroService: HeroService) {
  }

  ngOnInit() {
    this._heroService.getHeroes()
      .then(heroes => this.heroes = heroes.slice(1,5));
  }

  gotoDetail(hero: Hero) {
    let link = ['HeroDetail', { id: hero.id }];
    this._router.navigate(link);
  }
}