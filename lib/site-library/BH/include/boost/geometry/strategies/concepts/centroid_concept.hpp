// Boost.Geometry (aka GGL, Generic Geometry Library)

// Copyright (c) 2007-2012 Barend Gehrels, Amsterdam, the Netherlands.
// Copyright (c) 2008-2012 Bruno Lalande, Paris, France.
// Copyright (c) 2009-2012 Mateusz Loskot, London, UK.

// Parts of Boost.Geometry are redesigned from Geodan's Geographic Library
// (geolib/GGL), copyright (c) 1995-2010 Geodan, Amsterdam, the Netherlands.

// Use, modification and distribution is subject to the Boost Software License,
// Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_STRATEGIES_CONCEPTS_CENTROID_CONCEPT_HPP
#define BOOST_GEOMETRY_STRATEGIES_CONCEPTS_CENTROID_CONCEPT_HPP



#include <boost/concept_check.hpp>
<<<<<<< HEAD

=======
#include <boost/core/ignore_unused.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

namespace boost { namespace geometry { namespace concepts
{


/*!
    \brief Checks strategy for centroid
    \ingroup centroid
*/
template <typename Strategy>
class CentroidStrategy
{
#ifndef DOXYGEN_NO_CONCEPT_MEMBERS

    // 1) must define state_type,
    typedef typename Strategy::state_type state_type;

    // 2) must define point_type,
    typedef typename Strategy::point_type point_type;

    // 3) must define point_type, of polygon (segments)
    typedef typename Strategy::segment_point_type spoint_type;

    struct check_methods
    {
        static void apply()
        {
            Strategy *str = 0;
            state_type *st = 0;

            // 4) must implement a static method apply,
            // getting two segment-points
            spoint_type const* sp = 0;
            str->apply(*sp, *sp, *st);

            // 5) must implement a static method result
            //  getting the centroid
            point_type *c = 0;
            bool r = str->result(*st, *c);

<<<<<<< HEAD
            boost::ignore_unused_variable_warning(str);
            boost::ignore_unused_variable_warning(r);
=======
            boost::ignore_unused(str, r);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        }
    };

public :
    BOOST_CONCEPT_USAGE(CentroidStrategy)
    {
        check_methods::apply();
    }
#endif
};


}}} // namespace boost::geometry::concepts


#endif // BOOST_GEOMETRY_STRATEGIES_CONCEPTS_CENTROID_CONCEPT_HPP
