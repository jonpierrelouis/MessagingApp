import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { UserIdService } from 'src/app/service/user-id.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  submitEmail: string = "";
  submitPassword: string = "";

  constructor(private loginService: LoginService, private router: Router, private id: UserIdService) { }

  ngOnInit(): void {
  }

  loginUser(){
    
    this.loginService.loginUser(this.submitEmail, this.submitPassword).subscribe((resp) => {
      console.log(resp);
      this.id.setUserId(resp.userId);
      this.router.navigate(['main']);
    })
  }

  sendToRegister(){
    this.router.navigate(['register']);
  }

}
