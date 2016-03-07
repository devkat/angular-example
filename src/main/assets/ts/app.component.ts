import { Component } from '../../../../node_modules/angular2/core.d.ts';
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from '../../../../node_modules/angular2/router.d.ts';

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
  }
])

@Component({
  selector: 'my-app',
  template: `
    <h1>{{title}}</h1>
    <nav>
      <a [routerLink]="['Dashboard']">Dashboard</a>
      <a [routerLink]="['Heroes']">Heroes</a>
    </nav>
    <router-outlet></router-outlet>
  `,
  styleUrls: [ 'app/app.component.css' ],
  directives: [ ROUTER_DIRECTIVES ],
  providers: [
    ROUTER_PROVIDERS,
    HeroService
  ]
})

export class AppComponent {
  title = 'Tour of Heroes';
}