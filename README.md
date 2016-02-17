# android-volley-gson is example of android app that retrieve data from the server with Volley library and parse the data with Gson

How to add Volley and Gson Lib :

compile 'com.mcxiaoke.volley:library:1.0.19'
compile 'com.google.code.gson:gson:2.6.1'

Set Android permission on Manifest :

<uses-permission android:name="android.permission.INTERNET" />

How to get and parse data :

String url = "http://jsonplaceholder.typicode.com/posts";
StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG ).show();
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Post>>() {}.getType();
                posts = gson.fromJson(response, listType);
                PostAdapter postAdapter = new PostAdapter(MainActivity.this, R.layout.list_post, posts);
                listView.setAdapter(postAdapter);
                progressDialog.dismiss();
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                progressDialog.dismiss();
            }
        }){
};

RequestQueue requestQueue = Volley.newRequestQueue(this);
int socketTimeout = 30000;
RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
stringRequest.setRetryPolicy(policy);

requestQueue.add(stringRequest);