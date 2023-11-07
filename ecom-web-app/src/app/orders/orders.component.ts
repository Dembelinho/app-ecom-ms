import { Component,OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent  implements OnInit{
  orders : any;
  customerID!:number;
  constructor(private http:HttpClient, private router: Router, private route:ActivatedRoute) {
    this.customerID=route.snapshot.params['customerID']
  }
  ngOnInit(): void {
    this.http.get("http://localhost:9999/order-service/orders/search/byCustomerId?customerID="+this.customerID+"&projection=fullOrder")
      .subscribe({
        next : (data)=>{
          this.orders=data;
        },
        error : (err)=>{

        }
      });
  }

  getOrderDetails(o: any) {
    this.router.navigateByUrl("/order-details/"+o.id);
  }
}
