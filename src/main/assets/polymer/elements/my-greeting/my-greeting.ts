/// <reference path="../../../../../../target/web/web-modules/main/webjars/lib/polymer-ts/polymer-ts.d.ts" />

@component("my-greeting")
class MyGreeting extends polymer.Base {

  @property({ type: String, value: "Welcome to the heroes!", notify: true })
  greeting: string = "Welcome to the heroes!"

}

MyGreeting.register();