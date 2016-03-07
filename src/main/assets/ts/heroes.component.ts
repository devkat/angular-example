import {Component} from '../../../../node_modules/angular2/core.d.ts';
import {OnInit} from '../../../../node_modules/angular2/core.d.ts';
import {Router} from '../../../../node_modules/angular2/router.d.ts';

import {Hero} from './hero';
import {HeroDetailComponent} from './hero-detail.component.ts';
import {HeroService} from './hero.service.ts';

@Component({
  selector: 'my-heroes',
  templateUrl: 'app/heroes.component.html',
  styleUrls: [ 'app/heroes.component.css' ],
  directives: [ HeroDetailComponent ]
})

export class HeroesComponent implements OnInit {
  heroes: Hero[];
  selectedHero: Hero;

  constructor(
    private _router: Router,
    private _heroService: HeroService) {
  }

  ngOnInit() {
    this._heroService.getHeroes().then(heroes =>
      this.heroes = heroes);
  }

  onSelect(hero: Hero) {
    this.selectedHero = hero;
  }

  gotoDetail() {
    this._router.navigate(['HeroDetail', { id: this.selectedHero.id }]);
  }
}

