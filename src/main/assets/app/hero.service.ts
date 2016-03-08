import {Injectable} from 'angular2/core';
import {Http, Response} from 'angular2/http';
import {Observable} from 'rxjs/Observable';

import {Hero} from './hero';
import {HEROES} from './mock-heroes';

@Injectable()
export class HeroService {

  private _heroesUrl = 'api/heroes';

  constructor (private _http: Http) {}

  getHeroes() {
    return this._http.get(this._heroesUrl)
      .map(res => <Hero[]> res.json())
      //.do(data => console.log(data))
      .catch(this.handleError);
  }

  getHero(id: number) {
    return this._http.get(`${this._heroesUrl}/${id}`)
      .map(res => <Hero> res.json())
      //.do(data => console.log(data))
      .catch(this.handleError);
  }

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json().error || 'Server error');
  }
}