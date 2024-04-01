import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  submitEmail: string = "";
  submitPassword: string = "";

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
  }

  loginUser(){

    this.loginService.loginUser(this.submitEmail, this.submitPassword).subscribe((resp) => {
      console.log(resp);
    })
  }

}
