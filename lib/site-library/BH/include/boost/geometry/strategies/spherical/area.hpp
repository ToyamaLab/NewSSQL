// Boost.Geometry (aka GGL, Generic Geometry Library)

<<<<<<< HEAD
// Copyright (c) 2016-2017 Oracle and/or its affiliates.
=======
// Copyright (c) 2017 Adam Wulkiewicz, Lodz, Poland.

// Copyright (c) 2016-2018 Oracle and/or its affiliates.
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
// Contributed and/or modified by Vissarion Fisikopoulos, on behalf of Oracle
// Contributed and/or modified by Adam Wulkiewicz, on behalf of Oracle

// Use, modification and distribution is subject to the Boost Software License,
// Version 1.0. (See accompanying file LICENSE_1_0.txt or copy at
// http://www.boost.org/LICENSE_1_0.txt)

#ifndef BOOST_GEOMETRY_STRATEGIES_SPHERICAL_AREA_HPP
#define BOOST_GEOMETRY_STRATEGIES_SPHERICAL_AREA_HPP


#include <boost/geometry/formulas/area_formulas.hpp>
<<<<<<< HEAD
#include <boost/geometry/core/radius.hpp>
#include <boost/geometry/core/srs.hpp>
#include <boost/geometry/strategies/area.hpp>
=======
#include <boost/geometry/srs/sphere.hpp>
#include <boost/geometry/strategies/area.hpp>
#include <boost/geometry/strategies/spherical/get_radius.hpp>
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce


namespace boost { namespace geometry
{

namespace strategy { namespace area
{

<<<<<<< HEAD
=======

>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
/*!
\brief Spherical area calculation
\ingroup strategies
\details Calculates area on the surface of a sphere using the trapezoidal rule
<<<<<<< HEAD
\tparam PointOfSegment \tparam_segment_point
=======
\tparam RadiusTypeOrSphere \tparam_radius_or_sphere
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
\tparam CalculationType \tparam_calculation

\qbk{
[heading See also]
[link geometry.reference.algorithms.area.area_2_with_strategy area (with strategy)]
}
*/
template
<
<<<<<<< HEAD
    typename PointOfSegment,
=======
    typename RadiusTypeOrSphere = double,
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
    typename CalculationType = void
>
class spherical
{
    // Enables special handling of long segments
    static const bool LongSegment = false;

<<<<<<< HEAD
typedef typename boost::mpl::if_c
    <
        boost::is_void<CalculationType>::type::value,
        typename select_most_precise
            <
                typename coordinate_type<PointOfSegment>::type,
                double
            >::type,
        CalculationType
    >::type CT;

protected :
    struct excess_sum
    {
        CT m_sum;

        // Keep track if encircles some pole
        size_t m_crosses_prime_meridian;

        inline excess_sum()
            : m_sum(0)
            , m_crosses_prime_meridian(0)
        {}
        template <typename SphereType>
        inline CT area(SphereType sphere) const
        {
            CT result;
            CT radius = geometry::get_radius<0>(sphere);
=======
public:
    template <typename Geometry>
    struct result_type
        : strategy::area::detail::result_type
            <
                Geometry,
                CalculationType
            >
    {};

    template <typename Geometry>
    class state
    {
        friend class spherical;

        typedef typename result_type<Geometry>::type return_type;

    public:
        inline state()
            : m_sum(0)
            , m_crosses_prime_meridian(0)
        {}

    private:
        template <typename RadiusType>
        inline return_type area(RadiusType const& r) const
        {
            return_type result;
            return_type radius = r;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

            // Encircles pole
            if(m_crosses_prime_meridian % 2 == 1)
            {
                size_t times_crosses_prime_meridian
                        = 1 + (m_crosses_prime_meridian / 2);

<<<<<<< HEAD
                result = CT(2)
                         * geometry::math::pi<CT>()
                         * times_crosses_prime_meridian
                         - geometry::math::abs(m_sum);

                if(geometry::math::sign<CT>(m_sum) == 1)
=======
                result = return_type(2)
                         * geometry::math::pi<return_type>()
                         * times_crosses_prime_meridian
                         - geometry::math::abs(m_sum);

                if(geometry::math::sign<return_type>(m_sum) == 1)
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
                {
                    result = - result;
                }

            } else {
                result =  m_sum;
            }

            result *= radius * radius;

            return result;
        }
<<<<<<< HEAD
    };

public :
    typedef CT return_type;
    typedef PointOfSegment segment_point_type;
    typedef excess_sum state_type;
    typedef geometry::srs::sphere<CT> sphere_type;

    // For backward compatibility reasons the radius is set to 1
    inline spherical()
        : m_sphere(1.0)
    {}

    template <typename T>
    explicit inline spherical(geometry::srs::sphere<T> const& sphere)
        : m_sphere(geometry::get_radius<0>(sphere))
    {}

    explicit inline spherical(CT const& radius)
        : m_sphere(radius)
    {}

    inline void apply(PointOfSegment const& p1,
                      PointOfSegment const& p2,
                      excess_sum& state) const
    {
        if (! geometry::math::equals(get<0>(p1), get<0>(p2)))
        {
            typedef geometry::formula::area_formulas<CT> area_formulas;

            state.m_sum += area_formulas::template spherical<LongSegment>(p1, p2);
=======

        return_type m_sum;

        // Keep track if encircles some pole
        size_t m_crosses_prime_meridian;
    };

public :

    // For backward compatibility reasons the radius is set to 1
    inline spherical()
        : m_radius(1.0)
    {}

    template <typename RadiusOrSphere>
    explicit inline spherical(RadiusOrSphere const& radius_or_sphere)
        : m_radius(strategy_detail::get_radius
                    <
                        RadiusOrSphere
                    >::apply(radius_or_sphere))
    {}

    template <typename PointOfSegment, typename Geometry>
    inline void apply(PointOfSegment const& p1,
                      PointOfSegment const& p2,
                      state<Geometry>& st) const
    {
        if (! geometry::math::equals(get<0>(p1), get<0>(p2)))
        {
            typedef geometry::formula::area_formulas
                <
                    typename result_type<Geometry>::type
                > area_formulas;

            st.m_sum += area_formulas::template spherical<LongSegment>(p1, p2);
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce

            // Keep track whenever a segment crosses the prime meridian
            if (area_formulas::crosses_prime_meridian(p1, p2))
            {
<<<<<<< HEAD
                state.m_crosses_prime_meridian++;
=======
                st.m_crosses_prime_meridian++;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
            }
        }
    }

<<<<<<< HEAD
    inline return_type result(excess_sum const& state) const
    {
        return state.area(m_sphere);
    }

private :
    /// srs Sphere
    sphere_type m_sphere;
=======
    template <typename Geometry>
    inline typename result_type<Geometry>::type
        result(state<Geometry> const& st) const
    {
        return st.area(m_radius);
    }

private :
    typename strategy_detail::get_radius
        <
            RadiusTypeOrSphere
        >::type m_radius;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

#ifndef DOXYGEN_NO_STRATEGY_SPECIALIZATIONS

namespace services
{


<<<<<<< HEAD
template <typename Point>
struct default_strategy<spherical_equatorial_tag, Point>
{
    typedef strategy::area::spherical<Point> type;
};

// Note: spherical polar coordinate system requires "get_as_radian_equatorial"
template <typename Point>
struct default_strategy<spherical_polar_tag, Point>
{
    typedef strategy::area::spherical<Point> type;
=======
template <>
struct default_strategy<spherical_equatorial_tag>
{
    typedef strategy::area::spherical<> type;
};

// Note: spherical polar coordinate system requires "get_as_radian_equatorial"
template <>
struct default_strategy<spherical_polar_tag>
{
    typedef strategy::area::spherical<> type;
>>>>>>> ddff10c8c1a385735ed59fadb33c4b79e43db9ce
};

} // namespace services

#endif // DOXYGEN_NO_STRATEGY_SPECIALIZATIONS


}} // namespace strategy::area




}} // namespace boost::geometry

#endif // BOOST_GEOMETRY_STRATEGIES_SPHERICAL_AREA_HPP
