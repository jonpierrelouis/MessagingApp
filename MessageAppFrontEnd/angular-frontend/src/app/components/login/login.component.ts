import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  submitEmail: string = "";
  submitPassword: string = "";

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {
  }

  loginUser(){
    
    this.loginService.loginUser(this.submitEmail, this.submitPassword).subscribe((resp) => {
      console.log(resp);
      this.router.navigate(['main']);
    })
  }

  sendToRegister(){
    this.router.navigate(['register']);
  }

}
