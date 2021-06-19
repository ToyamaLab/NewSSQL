// Copyright (C) 2005 Arkadiy Vertleyb, Peder Holt.
// Use, modification and distribution is subject to the Boost Software
// License, Version 1.0. (http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_TYPEOF_STD_memory_hpp_INCLUDED
#define BOOST_TYPEOF_STD_memory_hpp_INCLUDED

#include <memory>
#include <boost/typeof/typeof.hpp>

#include BOOST_TYPEOF_INCREMENT_REGISTRATION_GROUP()

BOOST_TYPEOF_REGISTER_TEMPLATE(std::allocator, 1)
BOOST_TYPEOF_REGISTER_TEMPLATE(std::raw_storage_iterator, 2)
<<<<<<< HEAD
BOOST_TYPEOF_REGISTER_TEMPLATE(std::auto_ptr, 1)
=======
#ifndef BOOST_NO_AUTO_PTR
BOOST_TYPEOF_REGISTER_TEMPLATE(std::auto_ptr, 1)
#endif//BOOST_NO_AUTO_PTR
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#endif//BOOST_TYPEOF_STD_memory_hpp_INCLUDED
