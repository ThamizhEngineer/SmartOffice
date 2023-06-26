import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Rx'
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CartService {

    constructor(private http: HttpClient) {
    }
	
	private cart = [];

	getProducts() {
		return this.http.get('/assets/data/cart.json');
	}
	getaccordionItems(){
		return this.http.get('/assets/data/accordion.json');
	}
 
	getCart() {
		return this.cart;
	}
 
	addProduct(product) {
		this.cart.push(product);
	}
}