import {Injectable} from 'angular2/core';
import {Http, Response, Headers, RequestOptions} from 'angular2/http';
import {Observable} from 'rxjs/Observable';

import {Hero} from './hero';
import {HEROES} from './mock-heroes';

@Injectable()
export class HeroService {

  private _heroesUrl = 'api/heroes';

  constructor(private _http: Http) {}

  heroUrl(id: number): string {
    return `${this._heroesUrl}/${id}`;
  }

  list() {
    return this._http.get(this._heroesUrl)
      .map(res => <Hero[]> res.json())
      //.do(data => console.log(data))
      .catch(this.handleError);
  }

  find(id: number) {
    return this._http.get(this.heroUrl(id))
      .map(res => <Hero> res.json())
      //.do(data => console.log(data))
      .catch(this.handleError);
  }

  add(name: string): Observable<Hero> {
    let body = JSON.stringify({ name });
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return this._http.post(this._heroesUrl, body, options)
      .map(res => <Hero> res.json())
      .catch(this.handleError)
  }

  update(hero: Hero): Observable<Hero> {
    let body = JSON.stringify(hero);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return this._http.put(this.heroUrl(hero.id), body, options)
      .map(res => <Hero> res.json())
      .catch(this.handleError)
  }

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json().error || 'Server error');
  }
}