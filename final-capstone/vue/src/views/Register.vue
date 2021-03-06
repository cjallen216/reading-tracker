<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <div>
        <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
        </div>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-body">
        
        <div class="input-line">
        <!-- <label for="username" class="sr-only">Username</label> -->
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      </div>
      <div class="input-line">
        <!-- <label for="firstName" class="sr-only">First Name</label> -->
        <input type="text"
                id="first-name"
                class="form-control"
                placeholder="First Name"
                v-model="user.firstName"
                required>
        </div>
        <div class="input-line">
          <!-- <label for="lastName" class="sr-only">Last Name</label> -->
          <input type="text"
                  id="last-name"
                  class="form-control"
                  placeholder="Last Name"
                  v-model="user.lastName"
                  required>
          </div>
        <div class="input-line">
          <!-- <label for="email" class="sr-only">Email Address</label> -->
          <input type="email"
                  id="email-address"
                  class="form-control"
                  placeholder="Email Address"
                  v-model="user.emailAddress">
          </div>
      <div class="input-line">
      <!-- <label for="password" class="sr-only">Password</label> -->
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      </div>
      <div class="input-line">
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      </div>
      <div class="input-center">
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      </div>
      <div class="input-center">
      <button class="btn btn-lg btn-primary btn-block" type="submit" id="button">
        Create Account
      </button>
      </div>
      </div>
    </form>
      </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        firstName: '',
        lastName: '',
        emailAddress: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
body {
    font-family: "Roboto";
    font-size: 14px;
    background: linear-gradient(to right, #49D49D 10%, #A2C7E5 90%);
    height: 100%;
}

#register {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.form-register {
  /* text-align: center; */
  background-color: rgb(247, 244, 231);
  border-radius: 10px;
  width: 25%;
  box-shadow: 10px 10px #888888;
}

.input-center {
  text-align: center;
}

.form-register div{
  font-family: Arial, Helvetica, sans-serif;
  font-size: 1em;
  padding: 10px;
}

.form-body input {
  border-style: none;
  border-color: gray;
  background-color: rgb(247, 244, 231);
}

h1, p {
  font-family: Arial, Helvetica, sans-serif;
  text-align: center;
}

.input-line {
  border-style: none;
  border-bottom-style: solid;
  border-color: rgb(194, 194, 194);
}
</style>
