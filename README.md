# XCodebase ~ an android api to save variables online.

***This is an easy-to-use android api by which you can save & retrive data from an open-source database. just one word i.e ``` getX() & saveX() ``` (where X is type of variable) respectively for retriving & saving. No money, No time waste, No knowledge ,No Account Recquired. Just impliment & use***


## Implimentation




See [this library](https://github.com/ErrorxCode/OTP-Verification-Api) for more information regarding Email Verification.


### Pros
- Easy, simple & lightweight
- No money, No time waste, No knowledge, No Account Recquired.
- Best for simple data operations & small apps.
- Stores data in key-value pair

### Cons
- Not for complex querying or sorting data.
-  Limitation : Database Storage	0.25 GB
-  Limitation : Total Requests/month	10 K



## CRUD operations
#### Callback
```
OnXCodebaseResponse response =  new OnXCodebaseResponse(){
            @Override
            public void onSuccessful(@Nullable Object object) {
                // This object contains variable of the 'X' type that you have requested.
            }

            @Override
            public void onFailed(XCodeException e) {
                // e contain the cause of exception, understand it & try again.
                e.printStackTrace();
            }
        };
```
### Creating or Storing data:
```
saveX(String id,X value,response);
```
### Reading or fetching data:
```
X var = getX(String id,response);
```
### Updating or modifing data:
```
update(String id,Object value,response);
```
### Deleting or removing data:
```
delete(String id,response);
```

#### Nomenclature :- 
- *X : Data type , anyone of String,int or boolean.
- *id : The unique id of the variable by which it is being associated.
- *response : The callback which is invoked when performing any CRUD operation. You may pass null if you don't want to implement this.
- *value : The value of 'X' variable to update or to save.


