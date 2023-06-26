import { Component, Output, EventEmitter,NgModule} from '@angular/core';

@Component({
  selector: 'toggle-button',
  template: `
    <input type="checkbox" id="toggle-button-checkbox"
      (change)="changed.emit($event.target.checked)">
    <label class="toggle-button-switch"  
      for="toggle-button-checkbox"></label>
    <div class="toggle-button-text">
      <div class="toggle-button-text-on">ON</div>
      <div class="toggle-button-text-off">OFF</div>
    </div>
  `,
  styles: [`
    :host {
      display: block;
      position: relative;
      width: 70px;
      height: 50px;
    }
    
    input[type="checkbox"] {
      display: none; 
    }

    .toggle-button-switch {
      position: absolute;
      top: 2px;
      left: 2px;
      width: 35px;
      height: 20px;
      background-color: #fff;
      border-radius: 100%;
      cursor: pointer;
      z-index: 100;
      transition: left 0.3s;
    }

    .toggle-button-text {
      overflow: hidden;
      background-color: #B4B6BD;
      border-radius: 15px;
      box-shadow: 1px 6px 6px 0 rgba(50, 50, 50, 0.75);
      transition: background-color 2s;
    }

    .toggle-button-text-on,
    .toggle-button-text-off {
      float: left;
      width: 50%;
      height: 30%;
      line-height: 20px;
      font-family: Lato, sans-serif;
      font-weight: bold;
      color: #17202A;
      text-align: center;
    }

    input[type="checkbox"]:checked ~ .toggle-button-switch {
      left: 35px;
    }

    input[type="checkbox"]:checked ~ .toggle-button-text {
      background-color: #B4B6BD;
    }
  `]
})
export class ToggleButtonComponent  {
  @Output() changed = new EventEmitter<boolean>();
}
