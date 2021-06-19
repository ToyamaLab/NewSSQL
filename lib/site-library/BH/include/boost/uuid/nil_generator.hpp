// Boost nil_generator.hpp header file  ----------------------------------------------//

// Copyright 2010 Andy Tompkins.
// Distributed under the Boost Software License, Version 1.0. (See
// accompanying file LICENSE_1_0.txt or copy at
<<<<<<< HEAD
// http://www.boost.org/LICENSE_1_0.txt)
=======
// https://www.boost.org/LICENSE_1_0.txt)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

#ifndef BOOST_UUID_NIL_GENERATOR_HPP
#define BOOST_UUID_NIL_GENERATOR_HPP

#include <boost/uuid/uuid.hpp>

namespace boost {
namespace uuids {

// generate a nil uuid
struct nil_generator {
    typedef uuid result_type;
    
    uuid operator()() const {
        // initialize to all zeros
        uuid u = {{0}};
        return u;
    }
};

inline uuid nil_uuid() {
    return nil_generator()();
}

}} // namespace boost::uuids

#endif // BOOST_UUID_NIL_GENERATOR_HPP

