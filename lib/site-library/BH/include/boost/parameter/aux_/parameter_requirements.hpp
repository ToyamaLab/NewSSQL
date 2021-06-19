<<<<<<< HEAD
// Copyright Daniel Wallin, David Abrahams 2005. Use, modification and
// distribution is subject to the Boost Software License, Version 1.0. (See
// accompanying file LICENSE_1_0.txt or copy at
=======
// Copyright Daniel Wallin, David Abrahams 2005.
// Distributed under the Boost Software License, Version 1.0.
// (See accompanying file LICENSE_1_0.txt or copy at
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef PARAMETER_REQUIREMENTS_050331_HPP
#define PARAMETER_REQUIREMENTS_050331_HPP

<<<<<<< HEAD
namespace boost { namespace parameter { namespace aux {

// Used to pass static information about parameter requirements
// through the satisfies() overload set (below).  The
// matched function is never invoked, but its type indicates whether
// a parameter matches at compile-time
template <class Keyword, class Predicate, class HasDefault>
struct parameter_requirements
{
    typedef Keyword keyword;
    typedef Predicate predicate;
    typedef HasDefault has_default;
};

}}} // namespace boost::parameter::aux

#endif // PARAMETER_REQUIREMENTS_050331_HPP
=======
#include <boost/parameter/aux_/pack/parameter_requirements.hpp>

#endif  // include guard

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
