import { Component, OnInit } from 'angular2/core';
import { RouteParams } from 'angular2/router';
import { AbstractControl, ControlGroup, FormBuilder, FORM_DIRECTIVES } from 'angular2/common';
import { Observable } from 'rxjs/Observable';

import { Hero } from './hero';
import { HeroService } from './hero.service';


@Component({
  selector: 'hero-detail',
  inputs: ['hero'],
  templateUrl: 'app/hero-detail.component.html',
  styleUrls: [ 'app/hero-detail.component.css' ],
  directives: [FORM_DIRECTIVES],
})

export class HeroDetailComponent {
  model: Hero;
  active = true;
  heroForm: ControlGroup;

  constructor(
    private _heroService: HeroService,
    private _routeParams: RouteParams,
    private _formBuilder: FormBuilder) {
    this.heroForm = _formBuilder.group({
      "name": [ "" ]
    });
  }

  private setModel(hero: Hero) {
    this.model = hero;
    this.active = false;
    setTimeout(() => this.active = true, 0);
  }

  ngOnInit() {
    let id = +this._routeParams.get('id');
    if (id) {
      this._heroService.find(id).subscribe(
        hero => this.setModel(hero),
        error => console.log(error)
      )
    } else {
      this.model = {
        id: null,
        name: ""
      };
    }
  }

  private save(observable: Observable<Hero>) {
    observable.subscribe(
      hero => this.setModel(hero),
      error => this.showValidationErrors(error)
    );
  }

  onSubmit() {
    if (this.model.id === null) {
      this.save(this._heroService.add(this.model.name));
    } else {
      this.save(this._heroService.update(this.model));
    }
  }

  private showValidationErrors(errorMap: { [name: string]: string[] }) {
    console.log("Error: ", errorMap);
    for (name in errorMap) {
      let control = this.heroForm.controls[name];
      control.setErrors(errorMap[name], true);
      control.markAsDirty(true);
      console.log("control", control);
      console.log("errors", control.errors);
      console.log("valid?", control.valid);
    }
  }

  goBack() {
    window.history.back();
  }
}