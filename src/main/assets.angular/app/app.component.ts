import { Component } from 'angular2/core';
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from 'angular2/router';
import { HTTP_PROVIDERS } from 'angular2/http';

import { DashboardComponent } from './dashboard.component.ts';
import { HeroService } from './hero.service.ts';
import { HeroesComponent } from './heroes.component.ts';
import { HeroDetailComponent } from './hero-detail.component.ts';

@RouteConfig([
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: DashboardComponent,
    useAsDefault: true
  },
  {
    path: '/heroes',
    name: 'Heroes',
    component: HeroesComponent
  },
  {
    path: '/detail/:id',
    name: 'HeroDetail',
    component: HeroDetailComponent
  },
  {
    path: '/create',
    name: 'CreateHero',
    component: HeroDetailComponent
  }
])

@Component({
  selector: 'my-app',
  templateUrl: 'app/app.component.html',
  styleUrls: [ 'app/app.component.css' ],
  directives: [ ROUTER_DIRECTIVES ],
  providers: [
    ROUTER_PROVIDERS,
    HTTP_PROVIDERS,
    HeroService
  ]
})

export class AppComponent {
  title = 'Tour of Heroes';
}