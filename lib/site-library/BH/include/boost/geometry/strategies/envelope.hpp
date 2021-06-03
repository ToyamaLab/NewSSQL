// Boost.Geometry (aka GGL, Generic Geometry Library)

<<<<<<< HEAD
// Copyright (c) 2016-2017 Oracle and/or its affiliates.
=======
// Copyright (c) 2016-2018 Oracle and/or its affiliates.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
// Contributed and/or modified by Vissarion Fisikopoulos, on behalf of Oracle
// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle

// Use, modification and distribution is subject to the Boost Software License,
// Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_STRATEGIES_ENVELOPE_HPP
#define BOOST_GEOMETRY_STRATEGIES_ENVELOPE_HPP

#include <boost/mpl/assert.hpp>

namespace boost { namespace geometry
{


namespace strategy { namespace envelope { namespace services
{

/*!
\brief Traits class binding a default envelope strategy to a coordinate system
\ingroup util
<<<<<<< HEAD
\tparam CSTag tag of coordinate system
\tparam CalculationType \tparam_calculation
*/
template <typename CSTag, typename CalculationType = void>
=======
\tparam Tag tag of geometry
\tparam CSTag tag of coordinate system
\tparam CalculationType \tparam_calculation
*/
template <typename Tag, typename CSTag, typename CalculationType = void>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
struct default_strategy
{
    BOOST_MPL_ASSERT_MSG
        (
            false, NOT_IMPLEMENTED_FOR_THIS_TYPE
<<<<<<< HEAD
            , (types<CSTag>)
=======
            , (types<Tag, CSTag>)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
        );
};

}}} // namespace strategy::envelope::services


}} // namespace boost::geometry

#endif // BOOST_GEOMETRY_STRATEGIES_ENVELOPE_HPP

