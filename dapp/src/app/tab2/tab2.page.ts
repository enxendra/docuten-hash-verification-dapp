import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
//import { QRScanner, QRScannerStatus } from '@ionic-native/qr-scanner/ngx';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page {

  public hashInfo = { "id" : "-",
  "docHash" : "-",
  "ipfsHash" : "-",
  "documentOwner" : "-",
  "auditRegistries" : [],
                       };
  public hasError = false;
  public error = '';
  public endpoint = 'http://docuten-dapp.herokuapp.com' // default endpoint
  public hash = 'c4ecb5508773aef2d4a1c70339c375758741d8dd64c7a2d02e026c63b1c268ac'; //default hash

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.searchHash();
  }

 /* showQR(){
  // Optionally request the permission early
    this.qrScanner.prepare()
      .then((status: QRScannerStatus) => {
         if (status.authorized) {
           // camera permission was granted
           // start scanning
           let scanSub = this.qrScanner.scan().subscribe((text: string) => {
             this.hash = text;
             this.qrScanner.hide(); // hide camera preview
             scanSub.unsubscribe(); // stop scanning
           });

         } else if (status.denied) {
             this.error = 'Camera permission was permanently denied';
            // you must use QRScanner.openSettings() method to guide the user to the settings page
           // then they can grant the permission from there
         } else {
             this.error = 'Camera permission was denied, but not permanently. You can ask for permission again at a later time.';
         }
      })
      .catch((e: any) => this.error = e);
      //To do: Search by default some documents

   }*/

  searchHash(){
        this.http.get(this.endpoint+'/documentDetails?documentHash='+this.hash).subscribe(
              (data) => {
                  this.hashInfo = data['data'];
                  this.hasError = data['hasError'];
                  this.error = data['error'];
                  console.log(this.hashInfo);
              },
              (error) => {
                  console.error(error);
              }
          );
   }

}
