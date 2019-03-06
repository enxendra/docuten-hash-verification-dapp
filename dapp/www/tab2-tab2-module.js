(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["tab2-tab2-module"],{

/***/ "./src/app/tab2/tab2.module.ts":
/*!*************************************!*\
  !*** ./src/app/tab2/tab2.module.ts ***!
  \*************************************/
/*! exports provided: Tab2PageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Tab2PageModule", function() { return Tab2PageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _tab2_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./tab2.page */ "./src/app/tab2/tab2.page.ts");







var Tab2PageModule = /** @class */ (function () {
    function Tab2PageModule() {
    }
    Tab2PageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_3__["NgModule"])({
            imports: [
                _ionic_angular__WEBPACK_IMPORTED_MODULE_1__["IonicModule"],
                _angular_common__WEBPACK_IMPORTED_MODULE_4__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_5__["FormsModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild([{ path: '', component: _tab2_page__WEBPACK_IMPORTED_MODULE_6__["Tab2Page"] }])
            ],
            declarations: [_tab2_page__WEBPACK_IMPORTED_MODULE_6__["Tab2Page"]]
        })
    ], Tab2PageModule);
    return Tab2PageModule;
}());



/***/ }),

/***/ "./src/app/tab2/tab2.page.html":
/*!*************************************!*\
  !*** ./src/app/tab2/tab2.page.html ***!
  \*************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-header>\n  <ion-toolbar>\n    <ion-title>\n      Verify Hash - Docuten Blockchain Certification\n    </ion-title>\n  </ion-toolbar>\n</ion-header>\n\n<ion-content padding>\n\n  <ion-card padding>\n  <h2>Search hash</h2>\n  <form (ngSubmit)=\"searchHash()\">\n    <ion-item>\n      <ion-input type=\"text\" [(ngModel)]=\"hash\" name=\"hash\"></ion-input>\n    </ion-item>\n    <button ion-button type=\"submit\" block>Search</button>\n  </form>\n\n  </ion-card>\n  <ion-card padding>\n\n  <h2>Proof-of-existence</h2>\n  <ion-row>\n    <ion-col><b>Internal identifier</b></ion-col>\n    <ion-col>{{hashInfo.id}}</ion-col>\n  </ion-row>\n  <ion-row>\n    <ion-col><b>Hash</b></ion-col>\n    <ion-col>{{hashInfo.docHash}}</ion-col>\n  </ion-row>\n  <ion-row>\n    <ion-col><b>IPFS Hash</b></ion-col>\n    <ion-col>{{hashInfo.ipfsHash}}</ion-col>\n  </ion-row>\n  <ion-row>\n    <ion-col><b>Certification Account</b></ion-col>\n    <ion-col>{{hashInfo.documentOwner}}</ion-col>\n  </ion-row>\n\n  </ion-card>\n  <ion-card padding>\n\n  <h2>Proof-of-life</h2>\n  <ion-list inset>\n      <ion-row *ngFor=\"let event of hashInfo.auditRegistries\">\n        <ion-col>{{event.timestamp}}</ion-col> <ion-col>{{event.eventDescription}}</ion-col>\n      </ion-row>\n  </ion-list>\n\n  </ion-card>\n"

/***/ }),

/***/ "./src/app/tab2/tab2.page.scss":
/*!*************************************!*\
  !*** ./src/app/tab2/tab2.page.scss ***!
  \*************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "button {\n  background-color: #2ba3e1;\n  color: white;\n  font-size: 18px;\n  padding: 10px;\n  margin-top: 10px; }\n\nh2 {\n  color: black !important; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9Vc2Vycy94bXVuY2gvRGVza3RvcC9Qcm9qZWN0cy9KYXZhL2RvY3V0ZW4taGFzaC12ZXJpZmljYXRpb24tZEFwcC9kYXBwL3NyYy9hcHAvdGFiMi90YWIyLnBhZ2Uuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFFQTtFQUNJLHlCQUF5QjtFQUN6QixZQUFZO0VBQ1osZUFBZTtFQUNmLGFBQWE7RUFDYixnQkFBZ0IsRUFBQTs7QUFHcEI7RUFDSSx1QkFBdUIsRUFBQSIsImZpbGUiOiJzcmMvYXBwL3RhYjIvdGFiMi5wYWdlLnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyJcblxuYnV0dG9ue1xuICAgIGJhY2tncm91bmQtY29sb3I6ICMyYmEzZTE7XG4gICAgY29sb3I6IHdoaXRlO1xuICAgIGZvbnQtc2l6ZTogMThweDtcbiAgICBwYWRkaW5nOiAxMHB4O1xuICAgIG1hcmdpbi10b3A6IDEwcHg7XG59XG5cbmgye1xuICAgIGNvbG9yOiBibGFjayAhaW1wb3J0YW50O1xufSJdfQ== */"

/***/ }),

/***/ "./src/app/tab2/tab2.page.ts":
/*!***********************************!*\
  !*** ./src/app/tab2/tab2.page.ts ***!
  \***********************************/
/*! exports provided: Tab2Page */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "Tab2Page", function() { return Tab2Page; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");



var Tab2Page = /** @class */ (function () {
    function Tab2Page(http) {
        this.http = http;
        this.hashInfo = { "id": "-",
            "docHash": "-",
            "ipfsHash": "-",
            "documentOwner": "-",
            "auditRegistries": [],
        };
        this.hasError = false;
        this.error = '';
        this.endpoint = 'http://docuten-dapp.herokuapp.com'; // default endpoint
        this.hash = 'c4ecb5508773aef2d4a1c70339c375758741d8dd64c7a2d02e026c63b1c268ac'; //default hash
    }
    Tab2Page.prototype.ngOnInit = function () {
        //To do: Search by default some documents
        this.searchHash();
    };
    Tab2Page.prototype.searchHash = function () {
        var _this = this;
        this.http.get(this.endpoint + '/documentDetails?documentHash=' + this.hash).subscribe(function (data) {
            _this.hashInfo = data['data'];
            _this.hasError = data['hasError'];
            _this.error = data['error'];
            console.log(_this.hashInfo);
        }, function (error) {
            console.error(error);
        });
    };
    Tab2Page = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-tab2',
            template: __webpack_require__(/*! ./tab2.page.html */ "./src/app/tab2/tab2.page.html"),
            styles: [__webpack_require__(/*! ./tab2.page.scss */ "./src/app/tab2/tab2.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"]])
    ], Tab2Page);
    return Tab2Page;
}());



/***/ })

}]);
//# sourceMappingURL=tab2-tab2-module.js.map