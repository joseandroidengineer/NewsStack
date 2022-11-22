package com.jge.newsstack.models

class Pagination(private val limit:Int,
                private val offset:Int,
                private val count:Int,
                private val total:Int)